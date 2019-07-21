package com.revolut.technicalchallenge.account.dao;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.revolut.technicalchallenge.account.domain.Account;
import com.revolut.technicalchallenge.account.exceptions.AccountNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.sql.SQLException;

import static com.revolut.technicalchallenge.account.AccountsProvider.ACCOUNT_1;
import static org.junit.Assert.assertEquals;

public class AccountDAOTest {
  private AccountDAO accountDAO;

  @Before
  public void setup() throws SQLException {
    MockitoAnnotations.initMocks(this);
    ConnectionSource connectionSource = new JdbcPooledConnectionSource("jdbc:h2:mem:myDb");
    accountDAO = new AccountDAOImpl(connectionSource);
  }

  @Test(expected = AccountNotFoundException.class)
  public void getAccountShouldThrowExceptionIfAccountDoesNotExist() throws AccountNotFoundException {
    accountDAO.getAccount(2);
  }

  @Test
  public void updateAccountsShouldUpdateAccounts() throws AccountNotFoundException {
    accountDAO.createAccount(ACCOUNT_1);
    Account account = new Account(1, "Steve", BigDecimal.ZERO);
    accountDAO.updateAccounts(account);
    Account updatedAccount = accountDAO.getAccount(1);

    assertEquals(BigDecimal.ZERO, updatedAccount.getAmount());
  }
}
