package com.decathlon.techday.dddworkshop.studio.rest.dtos;

import com.decathlon.techday.dddworkshop.studio.domain.Instrument;

public class InstrumentDto {

  private String name;
  private String price;

  public InstrumentDto(Instrument instrument) {
    this.name = instrument.name();
    this.price = instrument.price() + " " + instrument.currency().toString();
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
