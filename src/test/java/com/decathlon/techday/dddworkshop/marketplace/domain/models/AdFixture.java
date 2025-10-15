package com.decathlon.techday.dddworkshop.marketplace.domain.models;

import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import java.util.Currency;

public class AdFixture {

  public static Ad FENDER(MusicianId musicianId) {
    return new Ad(musicianId, "Fender guitar", new Price(999f, Currency.getInstance("EUR")));
  }

  public static Ad GIBSON(MusicianId musicianId) {
    return new Ad(musicianId, "Gibson guitar", new Price(1200f, Currency.getInstance("EUR")));
  }

  public static Ad IBANEZ(MusicianId musicianId) {
    return new Ad(musicianId, "Ibanez guitar", new Price(1200f, Currency.getInstance("EUR")));
  }

}
