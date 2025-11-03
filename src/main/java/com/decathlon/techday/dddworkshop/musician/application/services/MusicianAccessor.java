package com.decathlon.techday.dddworkshop.musician.application.services;

import com.decathlon.techday.dddworkshop.musician.domain.models.Musician;
import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import java.util.Optional;

public interface MusicianAccessor {

  Optional<Musician> get(MusicianId musicianId);

}
