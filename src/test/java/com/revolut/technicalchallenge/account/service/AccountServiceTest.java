package com.revolut.technicalchallenge.account.service;

import com.revolut.technicalchallenge.account.dao.AccountDAO;
import com.revolut.technicalchallenge.account.domain.TransferDetails;
import com.revolut.technicalchallenge.account.exceptions.InsufficientAmountException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;

import static com.revolut.technicalchallenge.account.AccountsProvider.ACCOUNT_1;
import static com.revolut.technicalchallenge.account.AccountsProvider.ACCOUNT_2;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccountServiceTest {
  private AccountService accountService;

  @Mock private AccountDAO accountDAO;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    accountService = new AccountServiceImpl(accountDAO);
  }

  @Test(expected = InsufficientAmountException.class)
  public void transferShouldThrowExceptionIfFundsAreInsufficient() throws AccountNotFoundException, InsufficientAmountException {
    when(accountDAO.getAccount(1)).thenReturn(ACCOUNT_1);
    when(accountDAO.getAccount(2)).thenReturn(ACCOUNT_2);
    TransferDetails transferDetails = new TransferDetails(1, 2, BigDecimal.valueOf(300));

    accountService.transfer(transferDetails);
  }

  @Test
  public void transferShouldUpdateAccountWithUpdatedBalance() throws AccountNotFoundException, InsufficientAmountException {
    when(accountDAO.getAccount(1)).thenReturn(ACCOUNT_1);
    when(accountDAO.getAccount(2)).thenReturn(ACCOUNT_2);
    TransferDetails transferDetails = new TransferDetails(1, 2, BigDecimal.valueOf(80));
    accountService.transfer(transferDetails);

    assertEquals(BigDecimal.valueOf(20), ACCOUNT_1.getAmount());
    assertEquals(BigDecimal.valueOf(280), ACCOUNT_2.getAmount());
    verify(accountDAO, times(1)).updateAccounts(ACCOUNT_1, ACCOUNT_2);
  }
}
