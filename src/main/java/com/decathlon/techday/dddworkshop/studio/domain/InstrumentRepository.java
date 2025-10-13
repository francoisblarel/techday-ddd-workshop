package com.decathlon.techday.dddworkshop.studio.domain;

import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import java.util.List;

public interface InstrumentRepository {

  List<Instrument> getByMusician(MusicianId musicianId);

  void save(Instrument instrument);

}
