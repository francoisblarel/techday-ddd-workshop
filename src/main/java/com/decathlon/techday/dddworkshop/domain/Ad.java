package com.decathlon.techday.dddworkshop.domain;

import java.util.UUID;

public class Ad {

  private static final int ALMOST_SOLD_OUT_QUANTITIES = 5;
  private final UUID id;
  private final String name;
  private final String description;
  private AdStatus status;
  private Quantity quantity;
  private Price price;

  public Ad(UUID id, Price price, String name, String description, AdStatus status, Quantity quantity) {
    this.id = id;
    this.price = price;
    this.name = name;
    this.description = description;
    this.status = status;
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "Ad{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", description='" + description + '\'' +
      ", status=" + status +
      ", quantity=" + quantity +
      ", price=" + price +
      '}';
  }

  public UUID id() {
    return id;
  }

  public Price price() {
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

  public Quantity quantity() {
    return quantity;
  }

  public void applyDiscount(float discount) {
    if (quantity.value() < ALMOST_SOLD_OUT_QUANTITIES) {
      throw new RuntimeException("Too much quantity to apply discount");
    }

    this.price = price.discount(discount);
  }

  public void sell(Quantity quantityToSell) {
    if (!status.equals(AdStatus.PUBLISHED)) {
      throw new RuntimeException("Cannot sell items if Ad is not published");
    }

    this.quantity = quantity.decrease(quantityToSell);

    if (quantity.isNotAvailable()) {
      this.status = AdStatus.SOLD_OUT;
    }
  }

}
