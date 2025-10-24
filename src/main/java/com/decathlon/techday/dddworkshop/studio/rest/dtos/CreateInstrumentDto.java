package com.decathlon.techday.dddworkshop.studio.rest.dtos;

import com.decathlon.techday.dddworkshop.studio.domain.InstrumentDbEntity;
import java.util.Currency;
import java.util.UUID;

public class CreateInstrumentDto {

  private final String name;
  private final float amount;
  private final String currency;

  public CreateInstrumentDto(String name, float amount, String currency) {
    this.name = name;
    this.amount = amount;
    this.currency = currency;
  }

  public InstrumentDbEntity toEntity(UUID musicianId) {
    return new InstrumentDbEntity(UUID.randomUUID(), musicianId, name, amount, Currency.getInstance(currency));
  }

}
