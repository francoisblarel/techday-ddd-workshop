package com.decathlon.techday.dddworkshop.fixtures;

import com.decathlon.techday.dddworkshop.studio.domain.models.Instrument;
import com.decathlon.techday.dddworkshop.studio.domain.models.Price;
import com.decathlon.techday.dddworkshop.studio.domain.models.Quantity;
import java.util.Currency;
import java.util.UUID;

public class InstrumentFixture {

  public static Instrument DRAFT_SPECTOR_BASS(UUID userId) {
    return new Instrument(userId, "Spector bass", "Handcrafted Spector bass with DDD initiales",
      new Price(13.00f, Currency.getInstance("EUR")));
  }

  public static Instrument PUBLISHED_SPECTOR_BASS(UUID userId, int quantity) {
    Instrument instrument = DRAFT_SPECTOR_BASS(userId);
    try {
      instrument.publish(new Quantity(quantity));
    } catch (Exception e) {
      // ignore me, I'm a fixture :)
    }

    return instrument;
  }
}
