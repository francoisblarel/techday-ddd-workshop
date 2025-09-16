package com.decathlon.techday.dddworkshop.infrastructure;

import com.decathlon.techday.dddworkshop.domain.Price;
import jakarta.persistence.Embeddable;
import java.util.Currency;

@Embeddable
public class PriceDBEntity {

  private float amount;
  private String currency;

  public static PriceDBEntity fromPrice(Price price) {
    PriceDBEntity entity = new PriceDBEntity();
    entity.setAmount(price.amount());
    entity.setCurrency(price.currency().getCurrencyCode());

    return entity;
  }

  public void setAmount(float amount) {
    this.amount = amount;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public Price toPrice() {
    return new Price(amount, Currency.getInstance(currency));
  }
}
