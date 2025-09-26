package com.decathlon.techday.dddworkshop.musician.application.queries;

import com.decathlon.techday.dddworkshop.musician.application.queries.views.MusicianProfile;
import java.util.UUID;
import java.util.logging.Logger;

public class ViewMusicianProfile {

  private final Logger log = Logger.getLogger(ViewMusicianProfile.class.getName());
  private final MusicianReadModel readModel;

  public ViewMusicianProfile(MusicianReadModel readModel) {
    this.readModel = readModel;
  }

  public MusicianProfile viewProfile(UUID musicianId) {
    try {
      return readModel.getProfileByMusicianId(musicianId);
    } catch (Exception e) {
      log.warning("Error while viewing profile: " + e.getMessage());
      throw e;
    }
  }
}
