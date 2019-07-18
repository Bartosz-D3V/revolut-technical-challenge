package com.revolut.technicalchallenge.domain;

import java.math.BigDecimal;

public class Account {
  private int id;
  private String owner;
  private BigDecimal amount;

  public Account() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }
}
