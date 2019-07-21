package com.revolut.technicalchallenge.account.exceptions;

public class AccountNotFoundException extends Exception {
  public AccountNotFoundException(String message) {
    super(message);
  }
}
