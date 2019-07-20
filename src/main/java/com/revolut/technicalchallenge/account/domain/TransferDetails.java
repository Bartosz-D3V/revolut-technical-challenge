package com.revolut.technicalchallenge.account.domain;

import java.math.BigDecimal;

public class TransferDetails {
  private int sourceAccountId;
  private int destAccountId;
  private BigDecimal amount;

  public TransferDetails() {
  }

  public int getSourceAccountId() {
    return sourceAccountId;
  }

  public void setSourceAccountId(int sourceAccountId) {
    this.sourceAccountId = sourceAccountId;
  }

  public int getDestAccountId() {
    return destAccountId;
  }

  public void setDestAccountId(int destAccountId) {
    this.destAccountId = destAccountId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }
}
