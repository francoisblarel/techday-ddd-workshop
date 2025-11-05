package com.decathlon.techday.dddworkshop.marketplace.infrastructure.rest.dtos;

import com.decathlon.techday.dddworkshop.marketplace.domain.models.Price;
import java.math.BigDecimal;
import java.util.Currency;

public class PriceDto {

  private final BigDecimal amount;
  private final String currency;

  public PriceDto(BigDecimal amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public String getCurrency() {
    return currency;
  }

  public Price toPrice() {
    return new Price(amount, Currency.getInstance(currency));
  }
}
