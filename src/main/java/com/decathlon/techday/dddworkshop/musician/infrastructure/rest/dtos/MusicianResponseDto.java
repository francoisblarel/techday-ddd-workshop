package com.decathlon.techday.dddworkshop.musician.infrastructure.rest.dtos;

import com.decathlon.techday.dddworkshop.musician.domain.models.Musician;
import java.util.UUID;

public class MusicianResponseDto {

  private final UUID id;
  private final String name;
  private final String reputation;

  public MusicianResponseDto(Musician musician) {
    this.id = musician.getId().id();
    this.name = musician.getName();
    this.reputation = musician.getReputation().toString();
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getReputation() {
    return reputation;
  }
}
