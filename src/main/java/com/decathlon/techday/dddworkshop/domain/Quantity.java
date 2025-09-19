package com.decathlon.techday.dddworkshop.domain;

public record Quantity(int value) {

  public Quantity {
    if (value < 0) {
      throw new IllegalArgumentException("Quantity must be positive");
    }
  }

  public boolean isNotAvailable() {
    return value == 0;
  }

  public Quantity decrease(Quantity quantityToDecrease) {
    if (value < quantityToDecrease.value) {
      throw new IllegalArgumentException("Cannot decrease more than current value");
    }

    return new Quantity(value - quantityToDecrease.value());
  }

}
