package com.revolut.technicalchallenge.account;

import com.revolut.technicalchallenge.account.domain.Account;

import java.math.BigDecimal;

public class AccountsProvider {
  public final static Account ACCOUNT_1 = new Account(1, "John", BigDecimal.valueOf(100));
  public final static Account ACCOUNT_2 = new Account(2, "Steve", BigDecimal.valueOf(200));
}
