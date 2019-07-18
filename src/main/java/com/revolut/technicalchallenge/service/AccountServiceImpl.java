package com.revolut.technicalchallenge.service;

import java.util.Collection;
import java.util.Collections;

public class AccountServiceImpl implements AccountService {
  @Override
  public Collection<String> getAccounts() {
    return Collections.singleton("TEST");
  }
}
