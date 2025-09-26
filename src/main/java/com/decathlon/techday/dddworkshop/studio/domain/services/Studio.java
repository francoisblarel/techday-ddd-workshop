package com.decathlon.techday.dddworkshop.studio.domain.services;

import com.decathlon.techday.dddworkshop.musician.domain.models.Musician;
import com.decathlon.techday.dddworkshop.studio.domain.InstrumentRepository;
import com.decathlon.techday.dddworkshop.studio.domain.models.Instrument;
import com.decathlon.techday.dddworkshop.studio.domain.models.Quantity;
import com.decathlon.techday.dddworkshop.studio.domain.models.exceptions.InvalidInstrumentStatusException;

public class Studio {

  public static final int USER_PUBLISHED_ADS_LIMIT = 3;
  private final InstrumentRepository instrumentRepository;

  public Studio(InstrumentRepository instrumentRepository) {
    this.instrumentRepository = instrumentRepository;
  }

  public Instrument publishInstrument(Musician musician, Instrument instrument, Quantity quantity)
    throws InvalidInstrumentStatusException {
    long publishedAdsCount = instrumentRepository.getAllByUser(musician.getId())
      .stream()
      .filter(Instrument::isPublished)
      .count();

    if (publishedAdsCount >= USER_PUBLISHED_ADS_LIMIT) {
      // TODO replace all exceptions
      throw new RuntimeException("Musician published Instruments limit reached");
    }

    instrument.publish(quantity);

    return instrument;
  }

}
