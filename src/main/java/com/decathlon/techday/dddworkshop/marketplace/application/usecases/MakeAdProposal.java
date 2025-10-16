package com.decathlon.techday.dddworkshop.marketplace.application.usecases;

import com.decathlon.techday.dddworkshop.marketplace.application.usecases.commands.AdProposalCommand;
import com.decathlon.techday.dddworkshop.marketplace.domain.AdRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MakeAdProposal {

  private final AdRepository adRepository;
  private final Logger logger = LoggerFactory.getLogger(MakeAdProposal.class);

  public MakeAdProposal(AdRepository adRepository) {
    this.adRepository = adRepository;
  }

  public void execute(AdProposalCommand command) {
    adRepository.findById(command.adId())
      .stream()
      .peek(ad -> ad.doProposal(command.musicianId(), command.discountAsked()))
      .peek(adRepository::save)
      .peek(ad -> logger.info("Proposal made for Ad {}", ad.getId()));
  }
}
