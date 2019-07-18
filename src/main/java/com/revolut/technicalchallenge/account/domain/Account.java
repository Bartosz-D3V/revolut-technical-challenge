package com.revolut.technicalchallenge.account.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;

@DatabaseTable(tableName = "accounts")
public class Account {

  @DatabaseField(generatedId = true)
  private int id;

  @DatabaseField(canBeNull = false)
  private String owner;

  @DatabaseField(canBeNull = false)
  private BigDecimal amount = BigDecimal.ZERO;

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
