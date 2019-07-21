package com.revolut.technicalchallenge.account.controller;

import com.despegar.http.client.GetMethod;
import com.despegar.http.client.HttpClientException;
import com.despegar.http.client.HttpResponse;
import com.despegar.http.client.PostMethod;
import com.despegar.http.client.PutMethod;
import com.despegar.sparkjava.test.SparkServer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.revolut.technicalchallenge.account.domain.Account;
import com.revolut.technicalchallenge.account.domain.TransferDetails;
import com.revolut.technicalchallenge.account.exceptions.AccountNotFoundException;
import com.revolut.technicalchallenge.account.exceptions.InsufficientAmountException;
import com.revolut.technicalchallenge.account.service.AccountService;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mockito;
import spark.servlet.SparkApplication;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.revolut.technicalchallenge.account.AccountsProvider.ACCOUNT_1;
import static com.revolut.technicalchallenge.account.AccountsProvider.ACCOUNT_2;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class AccountControllerTest {
  private final static TransferDetails TRANSFER_DETAILS = new TransferDetails(1, 2, BigDecimal.TEN);
  private final static AccountService accountService = Mockito.mock(AccountService.class);
  private final static Gson GSON = new Gson();

  public static class WebAppTestSparkApp implements SparkApplication {
    public void init() {
      AccountController accountController = new AccountController(accountService, GSON);
      accountController.configureRoutes();
    }
  }

  @ClassRule
  public static SparkServer<WebAppTestSparkApp> testServer = new SparkServer<>(WebAppTestSparkApp.class, 4567);

  @Test
  public void GETShouldReturnCollectionOfAccounts() throws HttpClientException {
    when(accountService.getAccounts()).thenReturn(Arrays.asList(ACCOUNT_1, ACCOUNT_2));

    GetMethod request = testServer.get("/accounts", false);
    HttpResponse httpResponse = testServer.execute(request);
    Type listType = new TypeToken<Collection<Account>>() {}.getType();
    List<Account> accounts = GSON.fromJson(new String(httpResponse.body()), listType);

    assertEquals(200, httpResponse.code());
    assertEquals("application/json", httpResponse.headers().get("Content-Type").get(0));
    assertEquals(ACCOUNT_1, accounts.get(0));
    assertEquals(ACCOUNT_2, accounts.get(1));
  }

  @Test
  public void POSTShouldCreateAccount() throws HttpClientException {
    when(accountService.createAccount(ACCOUNT_1)).thenReturn(ACCOUNT_1);

    PostMethod request = testServer.post("/accounts", GSON.toJson(ACCOUNT_1), false);
    HttpResponse httpResponse = testServer.execute(request);
    Account account = GSON.fromJson(new String(httpResponse.body()), Account.class);

    assertEquals(201, httpResponse.code());
    assertEquals("application/json", httpResponse.headers().get("Content-Type").get(0));
    assertEquals(ACCOUNT_1, account);
  }

  @Test
  public void PUTTransferShouldTransferFunds() throws HttpClientException, InsufficientAmountException, AccountNotFoundException {
    doReturn(TRANSFER_DETAILS).when(accountService).transfer(TRANSFER_DETAILS);

    PutMethod request = testServer.put("/accounts/transfer", GSON.toJson(TRANSFER_DETAILS), false);
    HttpResponse httpResponse = testServer.execute(request);
    TransferDetails transferDetails = GSON.fromJson(new String(httpResponse.body()), TransferDetails.class);

    assertEquals(200, httpResponse.code());
    assertEquals("application/json", httpResponse.headers().get("Content-Type").get(0));
    assertEquals(TRANSFER_DETAILS, transferDetails);
  }

  @Test
  public void controllerShouldHandleInsufficientAmountException() throws HttpClientException, InsufficientAmountException, AccountNotFoundException {
    doThrow(new InsufficientAmountException("Insufficient amount.")).when(accountService).transfer(TRANSFER_DETAILS);

    PutMethod request = testServer.put("/accounts/transfer", GSON.toJson(TRANSFER_DETAILS), false);
    HttpResponse httpResponse = testServer.execute(request);
    InsufficientAmountException exception = GSON.fromJson(new String(httpResponse.body()), InsufficientAmountException.class);

    assertEquals(500, httpResponse.code());
    assertEquals("Insufficient amount.", exception.getMessage());
  }

  @Test
  public void controllerShouldHandleAccountNotFoundException() throws HttpClientException, InsufficientAmountException, AccountNotFoundException {
    doThrow(new AccountNotFoundException("Account not found.")).when(accountService).transfer(TRANSFER_DETAILS);

    PutMethod request = testServer.put("/accounts/transfer", GSON.toJson(TRANSFER_DETAILS), false);
    HttpResponse httpResponse = testServer.execute(request);
    AccountNotFoundException exception = GSON.fromJson(new String(httpResponse.body()), AccountNotFoundException.class);

    assertEquals(404, httpResponse.code());
    assertEquals("Account not found.", exception.getMessage());
  }

  @Test
  public void controllerShouldHandleIllegalArgumentException() throws HttpClientException, InsufficientAmountException, AccountNotFoundException {
    doThrow(new IllegalArgumentException("Amount cannot be negative")).when(accountService).transfer(TRANSFER_DETAILS);

    PutMethod request = testServer.put("/accounts/transfer", GSON.toJson(TRANSFER_DETAILS), false);
    HttpResponse httpResponse = testServer.execute(request);
    AccountNotFoundException exception = GSON.fromJson(new String(httpResponse.body()), AccountNotFoundException.class);

    assertEquals(500, httpResponse.code());
    assertEquals("Amount cannot be negative", exception.getMessage());
  }
}
