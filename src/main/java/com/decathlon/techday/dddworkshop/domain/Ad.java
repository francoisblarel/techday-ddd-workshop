package com.decathlon.techday.dddworkshop.domain;

import java.util.UUID;

public class Ad {

  private final UUID id;

  public Ad(UUID id) {
    this.id = id;
  }

  public UUID id() {
    return id;
  }
}
