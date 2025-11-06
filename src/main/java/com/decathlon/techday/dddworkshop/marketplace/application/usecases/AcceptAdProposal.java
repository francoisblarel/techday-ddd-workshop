package com.decathlon.techday.dddworkshop.marketplace.application.usecases;

import com.decathlon.techday.dddworkshop.marketplace.application.usecases.commands.AcceptAdProposalCommand;
import com.decathlon.techday.dddworkshop.marketplace.domain.AdRepository;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.Ad;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.InvalidAdStatusException;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.InvalidProposalStatusException;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.UnknownAdException;
import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AcceptAdProposal {

  private final AdRepository adRepository;
  private final Logger logger = LoggerFactory.getLogger(AcceptAdProposal.class);

  public AcceptAdProposal(AdRepository adRepository) {
    this.adRepository = adRepository;
  }

  public void execute(AcceptAdProposalCommand command)
    throws UnknownAdException, InvalidProposalStatusException, InvalidAdStatusException {
    UUID adId = command.adId();
    MusicianId musicianId = command.musicianId();

    Ad ad = adRepository.findById(adId)
      .orElseThrow(() -> new UnknownAdException("Unknown ad " + adId));

    ad.acceptMusicianProposal(musicianId);

    adRepository.save(ad);

    logger.info("Proposal from {} accepted for Ad {}", musicianId, adId);
  }
}
