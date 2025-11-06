package com.decathlon.techday.dddworkshop.marketplace.domain.models;

import java.util.Currency;

public record Price(float amount, Currency currency) {

  public Price {
    if (amount < 0) {
      throw new IllegalArgumentException("Amount must be positive");
    }
  }

  public Price discount(float percentage) {
    if (percentage <= 0) {
      throw new IllegalArgumentException("Discount amount must be greater than 0");
    }
    if (percentage > 100) {
      throw new IllegalArgumentException("Discount amount must be lower than 100");
    }

    float discountedAmount = amount * (100 - percentage) / 100.0f;

    return new Price(discountedAmount, currency);
  }
}
