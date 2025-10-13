package com.decathlon.techday.dddworkshop.marketplace.domain.models;

import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.InvalidAdStatusException;
import java.util.UUID;

public class Ad {

  private final UUID id;
  private final String instrument;
  private final Price price;
  private AdStatus status;

  public Ad(String instrument, Price price) {
    this.id = UUID.randomUUID();
    this.instrument = instrument;
    this.status = AdStatus.AVAILABLE;
    this.price = price;
  }

  public UUID getId() {
    return id;
  }

  public String getInstrument() {
    return instrument;
  }

  public Price getPrice() {
    return price;
  }

  public AdStatus getStatus() {
    return status;
  }

  public void sell() throws InvalidAdStatusException {
    if (status != AdStatus.AVAILABLE) {
      throw new InvalidAdStatusException("Cannot sell a non-available Ad");
    }

    this.status = AdStatus.SOLD_OUT;
  }
}
