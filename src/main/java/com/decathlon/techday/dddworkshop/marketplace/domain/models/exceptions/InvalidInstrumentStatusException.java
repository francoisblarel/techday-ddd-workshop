package com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions;

public class InvalidInstrumentStatusException extends Exception {

  public InvalidInstrumentStatusException(String message) {
    super(message);
  }
}
