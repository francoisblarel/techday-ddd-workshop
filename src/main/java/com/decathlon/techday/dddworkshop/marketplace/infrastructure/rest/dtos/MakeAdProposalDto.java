package com.decathlon.techday.dddworkshop.marketplace.infrastructure.rest.dtos;

import com.decathlon.techday.dddworkshop.marketplace.domain.models.Price;
import java.util.Currency;

public class MakeAdProposalDto {

  private final float price;
  private final String currency;

  public MakeAdProposalDto(float price, String currency) {
    this.price = price;
    this.currency = currency;
  }

  public Price toPrice() {
    return new Price(price, Currency.getInstance(currency));
  }
}
