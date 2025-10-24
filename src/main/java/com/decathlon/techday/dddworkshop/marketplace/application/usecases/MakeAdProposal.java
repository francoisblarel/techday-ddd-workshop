package com.decathlon.techday.dddworkshop.marketplace.application.usecases;

import com.decathlon.techday.dddworkshop.marketplace.application.usecases.commands.AdProposalCommand;
import com.decathlon.techday.dddworkshop.marketplace.domain.AdRepository;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.Ad;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.NonDecentProposalException;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.UnknownAdException;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MakeAdProposal {

  private final AdRepository adRepository;
  private final Logger logger = LoggerFactory.getLogger(MakeAdProposal.class);

  public MakeAdProposal(AdRepository adRepository) {
    this.adRepository = adRepository;
  }

  public void execute(AdProposalCommand command) throws NonDecentProposalException, UnknownAdException {
    UUID adId = command.adId();

    Ad ad = adRepository.findById(adId)
      .orElseThrow(() -> new UnknownAdException("Unknown add " + adId));

    ad.doProposal(command.musicianId(), command.desiredPrice());

    adRepository.save(ad);

    logger.info("Proposal made for Ad {}", ad.getId());
  }
}
