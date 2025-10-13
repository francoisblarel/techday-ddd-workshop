package com.decathlon.techday.dddworkshop.fixtures;

import com.decathlon.techday.dddworkshop.marketplace.domain.models.Instrument;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.Price;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.Quantity;
import java.util.Currency;
import java.util.UUID;

public class InstrumentFixture {

  public static Instrument DRAFT_SPECTOR_BASS(UUID musicianId) {
    return new Instrument(musicianId, "Spector bass", "Handcrafted Spector bass with DDD initiales",
      new Price(13.00f, Currency.getInstance("EUR")));
  }

  public static Instrument PUBLISHED_SPECTOR_BASS(UUID musicianId, int quantity) {
    Instrument instrument = DRAFT_SPECTOR_BASS(musicianId);
    try {
      instrument.publish(new Quantity(quantity));
    } catch (Exception e) {
      // ignore me, I'm a fixture :)
    }

    return instrument;
  }
}
