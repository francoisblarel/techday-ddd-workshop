package com.decathlon.techday.dddworkshop.marketplace.domain.models;

import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import java.math.BigDecimal;
import java.util.Currency;

public class AdFixture {

  public static Ad FENDER(MusicianId musicianId) {
    return new Ad(musicianId, "Fender guitar", new Price(BigDecimal.valueOf(999), Currency.getInstance("EUR")));
  }

  public static Ad GIBSON(MusicianId musicianId) {
    return new Ad(musicianId, "Gibson guitar", new Price(BigDecimal.valueOf(1200), Currency.getInstance("EUR")));
  }

  public static Ad IBANEZ(MusicianId musicianId) {
    return new Ad(musicianId, "Ibanez guitar", new Price(BigDecimal.valueOf(670), Currency.getInstance("EUR")));
  }

}
