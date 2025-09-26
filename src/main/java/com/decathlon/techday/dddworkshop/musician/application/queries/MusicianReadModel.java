package com.decathlon.techday.dddworkshop.musician.application.queries;

import com.decathlon.techday.dddworkshop.musician.application.queries.views.MusicianProfile;
import java.util.UUID;

public interface MusicianReadModel {

  MusicianProfile getProfileByMusicianId(UUID musicianId);
}
