package com.decathlon.techday.dddworkshop.musician.domain.models;

public class MusicianFixture {

  public static Musician PremiumMusician() {
    Musician jimiHendrix = new Musician("Jimi Hendrix");
    jimiHendrix.gainReputation();
    return jimiHendrix;
  }

  public static Musician NewMusician() {
    return new Musician("John Doe");
  }

}
