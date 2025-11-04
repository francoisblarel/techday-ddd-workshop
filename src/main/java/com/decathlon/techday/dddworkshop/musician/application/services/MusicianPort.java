package com.decathlon.techday.dddworkshop.musician.application.services;

import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import com.decathlon.techday.dddworkshop.shared.domain.expections.UnknownMusicianException;

public interface MusicianPort {

  boolean isPremium(MusicianId musicianId) throws UnknownMusicianException;

}
