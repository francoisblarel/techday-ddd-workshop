package com.decathlon.techday.dddworkshop.studio.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Currency;
import java.util.UUID;

@Entity
public final class InstrumentDbEntity {

  @Id
  private UUID id;
  private UUID musicianId;
  private String name;
  private float price;
  private Currency currency;

  public InstrumentDbEntity(UUID id, UUID musicianId, String name, float price, Currency currency) {
    this.id = id;
    this.musicianId = musicianId;
    this.name = name;
    this.price = price;
    this.currency = currency;
  }

  public InstrumentDbEntity() {
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setMusicianId(UUID musicianId) {
    this.musicianId = musicianId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }
}
