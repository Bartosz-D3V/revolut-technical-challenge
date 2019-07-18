package com.revolut.technicalchallenge.config;

import com.google.inject.AbstractModule;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.revolut.technicalchallenge.account.dao.AccountDAO;
import com.revolut.technicalchallenge.account.dao.AccountDAOImpl;
import com.revolut.technicalchallenge.account.service.AccountService;
import com.revolut.technicalchallenge.account.service.AccountServiceImpl;

import java.sql.SQLException;

public class MainModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(AccountService.class).to(AccountServiceImpl.class);
    try {
      bind(AccountDAO.class).toInstance(new AccountDAOImpl(new JdbcPooledConnectionSource("jdbc:h2:mem:myDb")));
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
