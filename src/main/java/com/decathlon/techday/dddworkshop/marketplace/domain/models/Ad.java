package com.decathlon.techday.dddworkshop.marketplace.domain.models;

import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.InvalidAdStatusException;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.InvalidProposalStatusException;
import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Ad {

  private final UUID id;
  private final MusicianId musicianId;
  private final String instrument; //
  private List<Proposal> proposals;
  private Price price;
  private AdStatus status;

  Ad(MusicianId musicianId, String instrument, Price price) {
    this.id = UUID.randomUUID();
    this.musicianId = musicianId;
    this.instrument = instrument;
    this.status = AdStatus.AVAILABLE;
    this.price = price;
    this.proposals = List.of();
  }

  public UUID getId() {
    return id;
  }

  public String getInstrument() {
    return instrument;
  }

  public Price getPrice() {
    return price;
  }

  public AdStatus getStatus() {
    return status;
  }

  public void sell() throws InvalidAdStatusException {
    if (status != AdStatus.AVAILABLE) {
      throw new InvalidAdStatusException("Cannot sell a non-available Ad");
    }

    this.status = AdStatus.SOLD_OUT;
  }

  public void applyDiscount(float percentage) {
    price = price.discount(percentage);
  }

  /**
   * Ensure there is only one proposal per musician
   */
  public void doProposal(MusicianId musicianId, float proposalPercentage) {
    Predicate<Proposal> isMusicianOtherProposal = (proposal) -> proposal.getMusicianId().equals(musicianId);
    Proposal newProposal = Proposal.proposeDiscount(musicianId, proposalPercentage, price);

    proposals = Stream.concat(
      // Remove old musician proposals
      proposals.stream().filter(isMusicianOtherProposal),
      // Add new proposal
      Stream.of(newProposal)
    ).toList();
  }

  public void acceptMusicianProposal(MusicianId musicianId)
    throws InvalidAdStatusException, InvalidProposalStatusException {
    if (status != AdStatus.AVAILABLE) {
      throw new InvalidAdStatusException("Cannot accept a proposal for a non-available Ad");
    }

    Optional<Proposal> maybeProposal = findMusicianProposal(musicianId);

    if (maybeProposal.isEmpty()) {
      throw new InvalidAdStatusException("Cannot accept a proposal because it does not exist");
    }

    maybeProposal.get().accept();

    this.sell();
  }

  public void rejectMusicianProposal(MusicianId musicianId)
    throws InvalidAdStatusException, InvalidProposalStatusException {
    if (status != AdStatus.AVAILABLE) {
      throw new InvalidAdStatusException("Cannot reject a proposal for a non-available Ad");
    }

    Optional<Proposal> maybeProposal = findMusicianProposal(musicianId);

    if (maybeProposal.isEmpty()) {
      throw new InvalidAdStatusException("Cannot reject a music proposal because it does not exist");
    }

    maybeProposal.get().reject();

  }

  private Optional<Proposal> findMusicianProposal(MusicianId musicianId) {
    return proposals.stream()
      .filter(proposal -> proposal.getMusicianId().equals(musicianId))
      .findFirst();
  }

  public MusicianId getMusicianId() {
    return musicianId;
  }
}
