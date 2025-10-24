package com.decathlon.techday.dddworkshop.marketplace.infrastructure.rest.dtos;

import com.decathlon.techday.dddworkshop.marketplace.domain.models.Proposal;
import java.util.UUID;

public class ProposalResponseDto {

  private final UUID musicianId;
  private final String desiredPrice;
  private final String status;

  public ProposalResponseDto(Proposal proposal) {
    this.musicianId = proposal.getMusicianId().id();
    this.desiredPrice =
      proposal.getDesiredPrice().amount() + " " + proposal.getDesiredPrice().currency().getCurrencyCode();
    this.status = proposal.getStatus().toString();
  }

  public UUID getMusicianId() {
    return musicianId;
  }

  public String getDesiredPrice() {
    return desiredPrice;
  }

  public String getStatus() {
    return status;
  }
}
