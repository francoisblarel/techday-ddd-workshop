package com.decathlon.techday.dddworkshop.marketplace.infrastructure.rest.dtos;

import com.decathlon.techday.dddworkshop.marketplace.domain.models.AdFactory.AdCommand;
import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;

public class PublishAdDto {

  private final String instrument;
  private final PriceDto price;

  public PublishAdDto(String instrument, PriceDto price) {
    this.instrument = instrument;
    this.price = price;
  }

  public String getInstrument() {
    return instrument;
  }

  public PriceDto getPrice() {
    return price;
  }

  public AdCommand toAdCommand(MusicianId musicianId) {
    return new AdCommand(musicianId, instrument, price.toPrice());
  }
}
