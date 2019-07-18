package com.revolut.technicalchallenge.account.service;

import com.revolut.technicalchallenge.account.domain.Account;

import java.util.Collection;

public interface AccountService {
  Collection<Account> getAccounts();

  Account createAccount(Account account);
}
