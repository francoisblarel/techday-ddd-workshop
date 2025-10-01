package com.decathlon.techday.dddworkshop.studio.domain.models.exceptions;

public class InstrumentLimitReachedException extends Exception {

  public InstrumentLimitReachedException(String message) {
    super(message);
  }
}
