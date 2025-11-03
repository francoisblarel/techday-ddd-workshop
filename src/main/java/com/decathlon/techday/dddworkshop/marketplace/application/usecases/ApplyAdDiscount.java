package com.decathlon.techday.dddworkshop.marketplace.application.usecases;

import com.decathlon.techday.dddworkshop.marketplace.application.usecases.commands.ApplyAdDiscountCommand;
import com.decathlon.techday.dddworkshop.marketplace.domain.AdRepository;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.Ad;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.UnknownAdException;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ApplyAdDiscount {

  private final AdRepository adRepository;
  private final Logger logger = LoggerFactory.getLogger(ApplyAdDiscount.class);

  public ApplyAdDiscount(AdRepository adRepository) {
    this.adRepository = adRepository;
  }

  public void execute(ApplyAdDiscountCommand command) throws UnknownAdException {
    UUID adId = command.adId();

    Ad ad = adRepository.findById(adId)
      .orElseThrow(() -> new UnknownAdException("Unknown ad " + adId));

    ad.applyDiscount(command.discount());

    adRepository.save(ad);

    logger.info("Discount applied for Ad {}", adId);
  }
}
