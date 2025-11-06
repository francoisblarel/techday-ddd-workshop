package com.decathlon.techday.dddworkshop.marketplace.infrastructure.rest.dtos;

import com.decathlon.techday.dddworkshop.marketplace.domain.models.Ad;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AdResponseDto {

  private final UUID id;
  private final UUID musicianId;
  private final String instrument;
  private final List<ProposalResponseDto> proposals;
  private final String price;
  private final String status;

  public AdResponseDto(Ad ad) {
    this.id = ad.getId();
    this.musicianId = ad.getMusicianId().id();
    this.instrument = ad.getInstrument();
    this.proposals = ad.getProposals().stream().map(ProposalResponseDto::new).collect(Collectors.toList());
    this.price = ad.getPrice().amount() + " " + ad.getPrice().currency().getCurrencyCode();
    this.status = ad.getStatus().toString();
  }

  public UUID getId() {
    return id;
  }

  public UUID getMusicianId() {
    return musicianId;
  }

  public String getInstrument() {
    return instrument;
  }

  public List<ProposalResponseDto> getProposals() {
    return proposals;
  }

  public String getPrice() {
    return price;
  }

  public String getStatus() {
    return status;
  }
}
