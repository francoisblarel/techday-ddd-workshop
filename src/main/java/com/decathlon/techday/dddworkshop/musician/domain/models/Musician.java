package com.decathlon.techday.dddworkshop.musician.domain.models;

import java.util.UUID;

public class Musician {

  private final UUID id;
  private final String name;
  private final Reputation reputation;
  // Many more fiels

  public Musician(String name) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.reputation = Reputation.NEW;
  }

  public UUID getId() {
    return id;
  }
}
