package com.decathlon.techday.dddworkshop.musician.application.services;

import com.decathlon.techday.dddworkshop.musician.domain.MusicianRepository;
import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import com.decathlon.techday.dddworkshop.shared.domain.expections.UnknownMusicianException;
import org.springframework.stereotype.Component;

@Component
public class MusicianApi implements MusicianPort {

  private final MusicianRepository musicianRepository;

  public MusicianApi(MusicianRepository musicianRepository) {
    this.musicianRepository = musicianRepository;
  }

  @Override
  public boolean isPremium(MusicianId musicianId) throws UnknownMusicianException {
    //TODO
    // return musicianRepository.findById(musicianId);
    return false;
  }
}
