package com.decathlon.techday.dddworkshop.infrastructure;

import com.decathlon.techday.dddworkshop.domain.Ad;
import com.decathlon.techday.dddworkshop.domain.AdRepository;
import java.util.Optional;
import java.util.UUID;

public class PostgresAdRepository implements AdRepository {

  private final SpringAdRepository springAdRepository;

  public PostgresAdRepository(SpringAdRepository springAdRepository) {
    this.springAdRepository = springAdRepository;
  }

  @Override
  public Ad save(Ad ad) {
    return springAdRepository.save(AdDBEntity.fromAd(ad))
      .toAd();
  }

  @Override
  public Optional<Ad> get(UUID id) {
    return springAdRepository.findById(id)
      .map(AdDBEntity::toAd);
  }
}
