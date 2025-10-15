package com.decathlon.techday.dddworkshop.studio.persistency;

import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import com.decathlon.techday.dddworkshop.studio.domain.InstrumentDbEntity;
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
  public List<InstrumentDbEntity> getByMusician(MusicianId musicianId) {
    return instrumentRepository.findAllByMusicianId(musicianId.id());
  }

  @Override
  public void save(InstrumentDbEntity instrument) {
    instrumentRepository.save(instrument);
  }
}
