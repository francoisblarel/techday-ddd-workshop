package com.decathlon.techday.dddworkshop.marketplace.infrastructure;

import com.decathlon.techday.dddworkshop.marketplace.domain.AdRepository;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.Ad;
import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryAdRepository implements AdRepository {

  private List<Ad> ads = List.of();

  @Override
  public void save(Ad ad) {
    ads = Stream.concat(ads.stream(), Stream.of(ad)).toList();
  }

  @Override
  public List<Ad> findByMusicianId(MusicianId musicianId) {
    return ads.stream().filter(ad -> ad.getMusicianId().equals(musicianId)).toList();
  }

  @Override
  public Optional<Ad> findById(UUID id) {
    return ads.stream().filter(ad -> ad.getId().equals(id)).findFirst();
  }
}
