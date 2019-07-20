package com.revolut.technicalchallenge.account.service;

import com.revolut.technicalchallenge.account.domain.Account;
import com.revolut.technicalchallenge.account.domain.TransferDetails;
import com.revolut.technicalchallenge.account.exceptions.InsufficientAmountException;

import java.util.Collection;

public interface AccountService {
  Collection<Account> getAccounts();

  Account getAccount(int id);

  Account createAccount(Account account);

  TransferDetails transfer(TransferDetails transferDetails) throws InsufficientAmountException;
}
