package com.revolut.technicalchallenge.account.dao;

import com.revolut.technicalchallenge.account.domain.Account;

import java.util.Collection;

public interface AccountDAO {
  Collection<Account> getAccounts();

  Account createAccount(Account account);
}