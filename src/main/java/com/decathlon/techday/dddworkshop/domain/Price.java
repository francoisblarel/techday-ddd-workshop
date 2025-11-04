package com.decathlon.techday.dddworkshop.domain;

import java.math.BigDecimal;
import java.util.Currency;

public record Price(BigDecimal amount, Currency currency) {

  public Price(String amount) {
    this(new BigDecimal(amount), Currency.getInstance("EUR"));
  }
}
