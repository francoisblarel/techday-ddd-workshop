package com.decathlon.techday.dddworkshop.marketplace.application.queries;

import com.decathlon.techday.dddworkshop.marketplace.application.queries.queries.GetAdQuery;
import com.decathlon.techday.dddworkshop.marketplace.application.queries.responses.GetAdResponse;
import com.decathlon.techday.dddworkshop.marketplace.domain.AdRepository;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.Ad;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class GetAd {

  private final AdRepository adRepository;

  public GetAd(AdRepository adRepository) {
    this.adRepository = adRepository;
  }

  public GetAdResponse handle(GetAdQuery query) {
    Optional<Ad> maybeAd = adRepository.findById(query.adId());

    return new GetAdResponse(maybeAd);
  }
}
