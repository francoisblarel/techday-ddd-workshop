package com.decathlon.techday.dddworkshop.domain;

import java.util.Optional;
import java.util.UUID;

public interface AdRepository {

  Ad save(Ad ad);

  Optional<Ad> get(UUID id);

}
