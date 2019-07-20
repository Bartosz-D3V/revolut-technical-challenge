package com.revolut.technicalchallenge.account.controller;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.revolut.technicalchallenge.account.domain.Account;
import com.revolut.technicalchallenge.account.domain.TransferDetails;
import com.revolut.technicalchallenge.account.exceptions.InsufficientAmountException;
import com.revolut.technicalchallenge.account.service.AccountService;

import static spark.Spark.before;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;
import static spark.Spark.put;

public class AccountController {
  private final AccountService accountService;
  private final Gson gson;

  @Inject
  public AccountController(AccountService accountService,
                           Gson gson) {
    this.accountService = accountService;
    this.gson = gson;
  }

  public void configureRoutes() {
    before((request, response) -> response.type("application/json"));
    exception(InsufficientAmountException.class, (exception, request, response) -> {
      response.body(gson.toJson(exception));
    });
    path("/accounts", () -> {
      get("", (request, response) -> accountService.getAccounts(), gson::toJson);
      post("", (request, response) -> accountService.createAccount(gson.fromJson(request.body(), Account.class)), gson::toJson);
      put("/transfer", (request, response) -> accountService.transfer(gson.fromJson(request.body(), TransferDetails.class)), gson::toJson);
    });
  }
}
