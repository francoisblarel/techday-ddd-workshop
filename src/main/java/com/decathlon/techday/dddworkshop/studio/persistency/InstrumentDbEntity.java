package com.decathlon.techday.dddworkshop.studio.persistency;

import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import com.decathlon.techday.dddworkshop.studio.domain.Instrument;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Currency;
import java.util.UUID;

@Entity
public final class InstrumentDbEntity {

  @Id
  private UUID id;
  private UUID musicianId;
  private String name;
  private float price;
  private Currency currency;

  public static InstrumentDbEntity fromInstrument(Instrument instrument) {
    InstrumentDbEntity entity = new InstrumentDbEntity();
    entity.setMusicianId(instrument.musicianId().id());
    entity.setId(instrument.id());
    entity.setName(instrument.name());
    entity.setPrice(instrument.price());
    entity.setCurrency(instrument.currency());

    return entity;
  }

  public Instrument toInstrument() {
    return new Instrument(id, new MusicianId(musicianId), name, price, currency);
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setMusicianId(UUID musicianId) {
    this.musicianId = musicianId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

}
