package com.decathlon.techday.dddworkshop.marketplace.domain.models;

import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.InvalidProposalStatusException;
import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import java.util.UUID;

public class Proposal {

  private final UUID id;
  private final MusicianId musicianId;
  private final float desiredDiscount;
  private final Price originalPrice;
  private ProposalStatus status;

  private Proposal(MusicianId musicianId, float desiredDiscount, Price originalPrice) {
    this.id = UUID.randomUUID();
    this.musicianId = musicianId;
    this.desiredDiscount = desiredDiscount;
    this.originalPrice = originalPrice;
    this.status = ProposalStatus.WAITING;
  }

  public static Proposal proposeDiscount(MusicianId musicianId, float desiredDiscount, Price originalPrice) {
    if (desiredDiscount <= 0 || desiredDiscount > 100) {
      throw new IllegalArgumentException("Discount must be between 0 and 100");
    }

    return new Proposal(musicianId, desiredDiscount, originalPrice);
  }

  public Price proposedPrice() {
    return originalPrice.discount(desiredDiscount);
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

  public MusicianId getMusicianId() {
    return musicianId;
  }
}
