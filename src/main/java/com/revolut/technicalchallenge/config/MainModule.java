package com.revolut.technicalchallenge.config;

import com.google.inject.AbstractModule;
import com.revolut.technicalchallenge.service.AccountService;
import com.revolut.technicalchallenge.service.AccountServiceImpl;

public class MainModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(AccountService.class).to(AccountServiceImpl.class);
  }
}
