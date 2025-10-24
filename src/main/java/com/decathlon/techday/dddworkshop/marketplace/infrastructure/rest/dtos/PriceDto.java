package com.decathlon.techday.dddworkshop.marketplace.infrastructure.rest.dtos;

import com.decathlon.techday.dddworkshop.marketplace.domain.models.Price;
import java.util.Currency;

public class PriceDto {

  private final float amount;
  private final String currency;

  public PriceDto(float amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public Price toPrice() {
    return new Price(amount, Currency.getInstance(currency));
  }
}
