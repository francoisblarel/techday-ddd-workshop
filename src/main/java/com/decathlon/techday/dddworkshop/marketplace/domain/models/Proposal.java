package com.decathlon.techday.dddworkshop.marketplace.domain.models;

import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.InvalidProposalStatusException;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.NonDecentProposalException;
import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;

public class Proposal {

  private static final double DECENT_THRESHOLD_RATIO = 0.6;
  private final MusicianId musicianId;
  private final Price desiredPrice;
  private ProposalStatus status;

  private Proposal(MusicianId musicianId, Price desiredPrice) {
    this.musicianId = musicianId;
    this.desiredPrice = desiredPrice;
    this.status = ProposalStatus.WAITING;
  }

  public static Proposal makeProposal(MusicianId musicianId, Price desiredPrice, Price originalPrice)
    throws NonDecentProposalException {
    float priceRatio = desiredPrice.amount() / originalPrice.amount();
    if (priceRatio < DECENT_THRESHOLD_RATIO) {
      throw new NonDecentProposalException("Proposal must be decent!");
    }

    return new Proposal(musicianId, desiredPrice);
  }

  public void accept() throws InvalidProposalStatusException {
    if (status != ProposalStatus.WAITING) {
      throw new InvalidProposalStatusException("Cannot accept a non-waiting proposal");
    }

    this.status = ProposalStatus.ACCEPTED;
  }

  public void reject() throws InvalidProposalStatusException {
    if (status != ProposalStatus.WAITING) {
      throw new InvalidProposalStatusException("Cannot reject a non-waiting proposal");
    }

    this.status = ProposalStatus.REJECTED;
  }

  public ProposalStatus getStatus() {
    return status;
  }

  public MusicianId getMusicianId() {
    return musicianId;
  }

  public Price getDesiredPrice() {
    return desiredPrice;
  }
}
