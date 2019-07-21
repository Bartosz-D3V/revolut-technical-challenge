package com.revolut.technicalchallenge.account.service;

import com.google.inject.Inject;
import com.revolut.technicalchallenge.account.dao.AccountDAO;
import com.revolut.technicalchallenge.account.domain.Account;
import com.revolut.technicalchallenge.account.domain.TransferDetails;
import com.revolut.technicalchallenge.account.exceptions.AccountNotFoundException;
import com.revolut.technicalchallenge.account.exceptions.InsufficientAmountException;

import java.math.BigDecimal;
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
  public Account getAccount(int id) throws AccountNotFoundException {
    return accountDAO.getAccount(id);
  }

  @Override
  public Account createAccount(Account account) {
    return accountDAO.createAccount(account);
  }

  @Override
  public TransferDetails transfer(TransferDetails transferDetails) throws InsufficientAmountException, com.revolut.technicalchallenge.account.exceptions.AccountNotFoundException {
    Account source = accountDAO.getAccount(transferDetails.getSourceAccountId());
    Account dest = accountDAO.getAccount(transferDetails.getDestAccountId());
    validateAmount(transferDetails.getAmount());
    validateAvailableFunds(source, transferDetails.getAmount());
    source.setAmount(source.getAmount().subtract(transferDetails.getAmount()));
    dest.setAmount(dest.getAmount().add(transferDetails.getAmount()));
    accountDAO.updateAccounts(source, dest);
    return transferDetails;
  }

  private void validateAmount(BigDecimal amount) {
    if (amount.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Amount must be positive");
    }
  }

  private void validateAvailableFunds(Account source, BigDecimal amount) throws InsufficientAmountException {
    if (source.getAmount().compareTo(amount) < 0) {
      throw new InsufficientAmountException(String.format("%s has insufficient funds.", source.getOwner()));
    }
  }
}
