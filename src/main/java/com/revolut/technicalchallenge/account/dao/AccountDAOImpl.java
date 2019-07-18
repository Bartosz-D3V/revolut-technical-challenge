package com.revolut.technicalchallenge.account.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.revolut.technicalchallenge.account.domain.Account;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class AccountDAOImpl extends BaseDaoImpl<Account, Integer> implements AccountDAO {
  public AccountDAOImpl(ConnectionSource connectionSource) throws SQLException {
    super(connectionSource, Account.class);
    TableUtils.createTableIfNotExists(connectionSource, Account.class);
  }

  @Override
  public Collection<Account> getAccounts() {
    Collection<Account> accounts = new ArrayList<>();
    super.forEach(accounts::add);
    return accounts;
  }

  @Override
  public Account createAccount(Account account) {
    try {
      super.create(account);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return account;
  }
}
