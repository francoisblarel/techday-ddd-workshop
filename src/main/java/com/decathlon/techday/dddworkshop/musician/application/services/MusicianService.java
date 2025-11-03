package com.decathlon.techday.dddworkshop.musician.application.services;

import com.decathlon.techday.dddworkshop.musician.domain.MusicianRepository;
import com.decathlon.techday.dddworkshop.musician.domain.models.Musician;
import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class MusicianService implements MusicianAccessor {

  private final MusicianRepository musicianRepository;

  public MusicianService(MusicianRepository musicianRepository) {
    this.musicianRepository = musicianRepository;
  }

  @Override
  public Optional<Musician> get(MusicianId musicianId) {
    return musicianRepository.findById(musicianId);
  }
}
