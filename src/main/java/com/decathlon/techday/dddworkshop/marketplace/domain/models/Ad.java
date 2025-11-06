package com.decathlon.techday.dddworkshop.marketplace.domain.models;

import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.InvalidAdStatusException;
import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import java.util.UUID;

public class Ad {

  private final UUID id;
  private final MusicianId musicianId;
  private final String instrument;
  private Price price;
  private AdStatus status;

  Ad(MusicianId musicianId, String instrument, Price price) {
    this.id = UUID.randomUUID();
    this.musicianId = musicianId;
    this.instrument = instrument;
    this.status = AdStatus.AVAILABLE;
    this.price = price;
  }

  public void sell() throws InvalidAdStatusException {
    if (status != AdStatus.AVAILABLE) {
      throw new InvalidAdStatusException("Cannot sell a non-available Ad");
    }

    this.status = AdStatus.SOLD_OUT;
  }

  public void applyDiscount(float percentage) {
    price = price.discount(percentage);
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

  public MusicianId getMusicianId() {
    return musicianId;
  }
}
