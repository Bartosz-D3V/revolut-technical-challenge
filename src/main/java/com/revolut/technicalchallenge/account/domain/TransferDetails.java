package com.revolut.technicalchallenge.account.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class TransferDetails {
  private int sourceAccountId;
  private int destAccountId;
  private BigDecimal amount;

  public TransferDetails() {
  }

  public TransferDetails(int sourceAccountId, int destAccountId, BigDecimal amount) {
    this.sourceAccountId = sourceAccountId;
    this.destAccountId = destAccountId;
    this.amount = amount;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TransferDetails)) return false;
    TransferDetails that = (TransferDetails) o;
    return getSourceAccountId() == that.getSourceAccountId() &&
      getDestAccountId() == that.getDestAccountId() &&
      Objects.equals(getAmount(), that.getAmount());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getSourceAccountId(), getDestAccountId(), getAmount());
  }
}
