package com.decathlon.techday.dddworkshop.domain;

import java.util.UUID;

public class Ad {

  private final UUID id;
  private final float price;
  private final String name;
  private final String description;
  private final AdStatus status;
  private final int quantity;

  public Ad(UUID id, float price, String name, String description, AdStatus status, int quantity) {
    this.id = id;
    this.price = price;
    this.name = name;
    this.description = description;
    this.status = status;
    this.quantity = quantity;
  }

  public UUID id() {
    return id;
  }

  public float price() {
    return price;
  }

  public String name() {
    return name;
  }

  public String description() {
    return description;
  }

  public AdStatus status() {
    return status;
  }

  public int quantity() {
    return quantity;
  }
}
