package com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions;

public class InstrumentLimitReachedException extends Exception {

  public InstrumentLimitReachedException(String message) {
    super(message);
  }
}
