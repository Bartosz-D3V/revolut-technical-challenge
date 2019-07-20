package com.revolut.technicalchallenge;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.revolut.technicalchallenge.account.controller.AccountController;
import com.revolut.technicalchallenge.config.MainModule;

public class Main {
  public static void main(final String[] args) {
    Injector injector = Guice.createInjector(new MainModule());

    AccountController accountController = injector.getInstance(AccountController.class);
    accountController.configureRoutes();
  }
}
