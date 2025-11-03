package com.decathlon.techday.dddworkshop.marketplace.infrastructure.rest.dtos;

public class ApplyDiscountDto {

  private final float discount;

  public ApplyDiscountDto(float discount) {
    this.discount = discount;
  }

  public float getDiscount() {
    return discount;
  }
}
