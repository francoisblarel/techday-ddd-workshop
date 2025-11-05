package com.decathlon.techday.dddworkshop.musician.application.services;

import com.decathlon.techday.dddworkshop.musician.domain.MusicianRepository;
import com.decathlon.techday.dddworkshop.musician.domain.models.Musician;
import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import com.decathlon.techday.dddworkshop.shared.domain.expections.UnknownMusicianException;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class MusicianApi implements MusicianPort {

  private final MusicianRepository musicianRepository;

  public MusicianApi(MusicianRepository musicianRepository) {
    this.musicianRepository = musicianRepository;
  }

  @Override
  public boolean isPremium(MusicianId musicianId) throws UnknownMusicianException {
    Optional<Musician> maybeMusician = musicianRepository.findById(musicianId);

    if (maybeMusician.isEmpty()) {
      throw new UnknownMusicianException("Unknown musician " + musicianId);
    }

    return maybeMusician.get().isPremium();
  }
}
