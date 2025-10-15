package com.decathlon.techday.dddworkshop.musician.domain.models;

import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import java.util.UUID;

public class Musician {

  private final MusicianId id;
  private final String name;
  private Reputation reputation;
  // Many more fiels

  public Musician(String name) {
    this.id = new MusicianId(UUID.randomUUID());
    this.name = name;
    this.reputation = Reputation.NEW;
  }

  public void gainReputation() {
    this.reputation = Reputation.PREMIUM;
  }

  public boolean isPremium() {
    return reputation == Reputation.PREMIUM;
  }

  public MusicianId getId() {
    return id;
  }
}
