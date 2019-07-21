package com.revolut.technicalchallenge.account.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Objects;

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

  public Account(int id, String owner, BigDecimal amount) {
    this.id = id;
    this.owner = owner;
    this.amount = amount;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Account)) return false;
    Account account = (Account) o;
    return getId() == account.getId() &&
      Objects.equals(getOwner(), account.getOwner()) &&
      Objects.equals(getAmount(), account.getAmount());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getOwner(), getAmount());
  }
}
