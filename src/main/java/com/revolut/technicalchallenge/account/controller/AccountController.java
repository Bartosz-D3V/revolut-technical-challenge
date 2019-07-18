package com.revolut.technicalchallenge.account.controller;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.revolut.technicalchallenge.account.domain.Account;
import com.revolut.technicalchallenge.account.service.AccountService;

import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;

public class AccountController {
  private final AccountService accountService;

  @Inject
  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  public void configureRoutes(Gson gson) {
    path("/accounts", () -> {
      get("", (request, response) -> accountService.getAccounts(), gson::toJson);
      post("", (request, response) -> accountService.createAccount(new Gson().fromJson(request.body(), Account.class)), gson::toJson);
    });
  }
}
