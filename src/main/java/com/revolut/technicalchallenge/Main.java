package com.revolut.technicalchallenge;

import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.revolut.technicalchallenge.config.MainModule;
import com.revolut.technicalchallenge.controller.AccountController;

import java.util.logging.Logger;

public class Main {
  private final static Logger logger = Logger.getLogger(Main.class.getName());

  public static void main(final String[] args) {
    Injector injector = Guice.createInjector(new MainModule());
    Gson gson = new Gson();

    AccountController accountController = injector.getInstance(AccountController.class);
    accountController.configureRoutes(gson);
  }
}
