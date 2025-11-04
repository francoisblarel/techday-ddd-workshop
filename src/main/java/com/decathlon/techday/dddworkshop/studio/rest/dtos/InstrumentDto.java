package com.decathlon.techday.dddworkshop.studio.rest.dtos;

import com.decathlon.techday.dddworkshop.studio.domain.InstrumentDbEntity;

public class InstrumentDto {

  private String name;
  private String price;

  public InstrumentDto(InstrumentDbEntity instrument) {
    this.name = instrument.getName();
    this.price = instrument.getPrice() + " " + instrument.getCurrency().getCurrencyCode();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

}
