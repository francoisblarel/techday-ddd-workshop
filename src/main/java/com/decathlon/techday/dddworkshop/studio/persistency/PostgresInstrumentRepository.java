package com.decathlon.techday.dddworkshop.studio.persistency;

import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import com.decathlon.techday.dddworkshop.studio.domain.Instrument;
import com.decathlon.techday.dddworkshop.studio.domain.InstrumentRepository;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PostgresInstrumentRepository implements InstrumentRepository {

  private final SpringInstrumentRepository instrumentRepository;

  public PostgresInstrumentRepository(SpringInstrumentRepository instrumentRepository) {
    this.instrumentRepository = instrumentRepository;
  }

  @Override
  public List<Instrument> getByMusician(MusicianId musicianId) {
    return instrumentRepository.findAllByMusicianId(musicianId.id())
      .stream()
      .map(InstrumentDbEntity::toInstrument)
      .toList();
  }

  @Override
  public void save(Instrument instrument) {
    instrumentRepository.save(InstrumentDbEntity.fromInstrument(instrument));
  }
}
