package com.decathlon.techday.dddworkshop.marketplace.application.queries;

import com.decathlon.techday.dddworkshop.marketplace.application.queries.commands.GetAdCommand;
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

  public GetAdResponse execute(GetAdCommand command) {
    Optional<Ad> maybeAd = adRepository.findById(command.adId());

    return new GetAdResponse(maybeAd);
  }
}
