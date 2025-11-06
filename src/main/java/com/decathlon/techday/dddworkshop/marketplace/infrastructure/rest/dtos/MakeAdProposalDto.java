package com.decathlon.techday.dddworkshop.marketplace.infrastructure.rest.dtos;

public class MakeAdProposalDto {

  private final PriceDto price;

  public MakeAdProposalDto(PriceDto price) {
    this.price = price;
  }

  public PriceDto getPrice() {
    return price;
  }
}
