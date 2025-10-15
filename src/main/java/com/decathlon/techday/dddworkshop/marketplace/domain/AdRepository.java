package com.decathlon.techday.dddworkshop.marketplace.domain;

import com.decathlon.techday.dddworkshop.marketplace.domain.models.Ad;
import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import java.util.List;

public interface AdRepository {

  void save(Ad ad);
  
  List<Ad> findByMusicianId(MusicianId musicianId);
}
