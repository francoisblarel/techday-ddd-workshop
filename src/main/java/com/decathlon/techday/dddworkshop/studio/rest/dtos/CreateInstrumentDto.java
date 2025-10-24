package com.decathlon.techday.dddworkshop.studio.rest.dtos;

import com.decathlon.techday.dddworkshop.studio.domain.InstrumentDbEntity;
import java.util.Currency;
import java.util.UUID;

public class CreateInstrumentDto {

  private final String name;
  private final float price;
  private final String currency;

  public CreateInstrumentDto(String name, float price, String currency) {
    this.name = name;
    this.price = price;
    this.currency = currency;
  }

  public InstrumentDbEntity toEntity(UUID musicianId) {
    return new InstrumentDbEntity(UUID.randomUUID(), musicianId, name, price, Currency.getInstance(currency));
  }

}
