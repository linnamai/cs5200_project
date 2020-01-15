package com.dumplings.pleyt.model;

import java.math.BigInteger;

public class CreditCards {
  protected int CardId;
  protected BigInteger CardNumber;
  protected int ExpirationMonth;
  protected int ExpirationYear;
  protected Customers customer;

  public CreditCards(int cardId, BigInteger cardNumber, int expirationMonth, int expirationYear, Customers customer) {
    this.CardId = cardId;
    this.CardNumber = cardNumber;
    this.ExpirationMonth = expirationMonth;
    this.ExpirationYear = expirationYear;
    this.customer = customer;
  }

  public int getCardId() {
    return CardId;
  }

  public void setCardId(int cardId) {
    this.CardId = cardId;
  }

  public BigInteger getCardNumber() {
    return CardNumber;
  }

  public void setCardNumber(BigInteger cardNumber) {
    this.CardNumber = cardNumber;
  }

  public int getExpirationMonth() {
    return ExpirationMonth;
  }

  public void setExpirationMonth(int expirationMonth) {
    this.ExpirationMonth = expirationMonth;
  }

  public int getExpirationYear() {
    return ExpirationYear;
  }

  public void setExpirationYear(int expirationYear) {
    this.ExpirationYear = expirationYear;
  }

  public Customers getCustomer() {
    return customer;
  }

  public void setCustomer(Customers customer) {
    this.customer = customer;
  }
}
