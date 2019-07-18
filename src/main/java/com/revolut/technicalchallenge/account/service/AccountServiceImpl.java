package com.revolut.technicalchallenge.account.service;

import com.google.inject.Inject;
import com.revolut.technicalchallenge.account.dao.AccountDAO;
import com.revolut.technicalchallenge.account.domain.Account;

import java.util.Collection;

public class AccountServiceImpl implements AccountService {
  private final AccountDAO accountDAO;

  @Inject
  public AccountServiceImpl(AccountDAO accountDAO) {
    this.accountDAO = accountDAO;
  }

  @Override
  public Collection<Account> getAccounts() {
    return accountDAO.getAccounts();
  }

  @Override
  public Account createAccount(Account account) {
    return accountDAO.createAccount(account);
  }
}
