package com.decathlon.techday.dddworkshop.musician.domain;

import com.decathlon.techday.dddworkshop.musician.domain.models.Musician;
import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import java.util.List;
import java.util.Optional;

public interface MusicianRepository {

  void save(Musician musician);

  List<Musician> findAll();

  Optional<Musician> findById(MusicianId id);
}
