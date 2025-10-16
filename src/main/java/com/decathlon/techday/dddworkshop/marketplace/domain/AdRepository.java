package com.decathlon.techday.dddworkshop.marketplace.domain;

import com.decathlon.techday.dddworkshop.marketplace.domain.models.Ad;
import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AdRepository {

  void save(Ad ad);

  List<Ad> findByMusicianId(MusicianId musicianId);

  Optional<Ad> findById(UUID id);
}
