package com.decathlon.techday.dddworkshop.studio.domain;

import com.decathlon.techday.dddworkshop.studio.domain.models.Instrument;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InstrumentRepository {

  Instrument save(Instrument instrument);

  Optional<Instrument> get(UUID id);

  List<Instrument> getAllByUser(UUID userId);

}
