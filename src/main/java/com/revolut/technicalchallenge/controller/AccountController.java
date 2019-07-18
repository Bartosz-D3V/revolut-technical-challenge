package com.revolut.technicalchallenge.controller;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.revolut.technicalchallenge.service.AccountService;

import static spark.Spark.get;
import static spark.Spark.path;

public class AccountController {
  private final AccountService accountService;

  @Inject
  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  public void configureRoutes(Gson gson) {
    path("/accounts", () -> {
      get("", (request, response) -> accountService.getAccounts(), gson::toJson);
    });
  }
}
