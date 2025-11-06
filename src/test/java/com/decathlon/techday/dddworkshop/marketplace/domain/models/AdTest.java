package com.decathlon.techday.dddworkshop.marketplace.domain.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;

import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.InvalidAdStatusException;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.InvalidProposalStatusException;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.NonDecentProposalException;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.UnknownAdProposalException;
import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class AdTest {

  private final MusicianId musicianId = new MusicianId(UUID.randomUUID());
  private final MusicianId anotherMusicianId = new MusicianId(UUID.randomUUID());

  private static List<Proposal> getMusicianProposals(Ad ad, MusicianId musicianId1) {
    return ad.getProposals().stream()
      .filter(proposal -> proposal.getMusicianId().equals(musicianId1))
      .toList();
  }

  @Test
  @DisplayName("When creating an Ad, the status is Available")
  void constructor() {
    Ad cut = new Ad(musicianId, "Fender American Professional 2", new Price(new BigDecimal("1999.99"),
      Currency.getInstance("EUR")));

    assertThat(cut.getStatus()).isEqualTo(AdStatus.AVAILABLE);
  }

  @Test
  @DisplayName("When applying discount, the price is updated")
  void applyDiscount() {
    Price originalPrice = new Price(new BigDecimal("1999.99"), Currency.getInstance("EUR"));
    Ad cut = new Ad(musicianId, "Fender American Professional 2", originalPrice);

    cut.applyDiscount(20f);

    assertThat(cut.getPrice()).isNotEqualTo(originalPrice);
  }

  @Nested
  class Sell {

    @Test
    @DisplayName("When successfully selling an Ad, it is now SOLD_OUT")
    void success() throws InvalidAdStatusException {
      Ad cut = new Ad(musicianId, "Fender American Professional 2", new Price(new BigDecimal("1999.99"),
        Currency.getInstance("EUR")));

      cut.sell();

      assertThat(cut.getStatus()).isEqualTo(AdStatus.SOLD_OUT);
    }

    @Test
    @DisplayName("When selling a non-available Ad, it throws an exception")
    void nonAvailableAd() throws InvalidAdStatusException {
      Ad cut = new Ad(musicianId, "Fender American Professional 2", new Price(new BigDecimal("1999.99"),
        Currency.getInstance("EUR")));

      cut.sell();

      assertThatException()
        .isThrownBy(cut::sell)
        .isInstanceOf(InvalidAdStatusException.class)
        .withMessageContaining("Cannot sell a non-available Ad");
    }
  }

  @Nested
  class MakeProposal {

    @Test
    @DisplayName("When making a proposal for a non-available Ad, it throws an exception")
    void non_available_ad() throws InvalidAdStatusException {
      Ad cut = new Ad(musicianId, "Fender American Professional 2",
        new Price(BigDecimal.valueOf(1999.99), Currency.getInstance("EUR")));

      cut.sell();

      assertThatException()
        .isThrownBy(() -> cut.makeProposal(musicianId,
          new Price(BigDecimal.valueOf(1799.99), Currency.getInstance("EUR"))))
        .isInstanceOf(InvalidAdStatusException.class)
        .withMessage("Cannot make a proposal for a non-available Ad");
    }

    @Test
    @DisplayName("When a musician makes a proposal, it is added to the existing ones")
    void non_existing_musician_proposal() throws NonDecentProposalException, InvalidAdStatusException {
      Ad cut = new Ad(musicianId, "Fender American Professional 2",
        new Price(BigDecimal.valueOf(1999.99), Currency.getInstance("EUR")));
      int totalProposals = cut.getProposals().size();

      cut.makeProposal(musicianId, new Price(BigDecimal.valueOf(1799.99), Currency.getInstance("EUR")));

      assertThat(getMusicianProposals(cut, musicianId)).hasSize(1);
      assertThat(cut.getProposals()).hasSize(totalProposals + 1);
    }

    @Test
    @DisplayName("When a musician makes a new proposal, it overrides the existing one")
    void existing_musician_proposal() throws NonDecentProposalException, InvalidAdStatusException {
      Ad cut = new Ad(musicianId, "Fender American Professional 2",
        new Price(BigDecimal.valueOf(1999.99), Currency.getInstance("EUR")));
      cut.makeProposal(anotherMusicianId, new Price(BigDecimal.valueOf(1895), Currency.getInstance("EUR")));
      cut.makeProposal(musicianId, new Price(BigDecimal.valueOf(1650), Currency.getInstance("EUR")));

      cut.makeProposal(musicianId, new Price(BigDecimal.valueOf(1799.99), Currency.getInstance("EUR")));

      assertThat(getMusicianProposals(cut, musicianId)).hasSize(1);
    }
  }

  @Nested
  class RejectProposal {

    @Test
    @DisplayName("When rejecting a proposal for an non-available ad, it throws an exception")
    void invalidAdStatus() throws InvalidAdStatusException, NonDecentProposalException {
      Ad cut = new Ad(musicianId, "Fender American Professional 2",
        new Price(BigDecimal.valueOf(1999.99), Currency.getInstance("EUR")));

      cut.makeProposal(anotherMusicianId, new Price(BigDecimal.valueOf(1799.99), Currency.getInstance("EUR")));

      cut.sell();

      assertThatException()
        .isThrownBy(() -> cut.rejectMusicianProposal(anotherMusicianId))
        .isInstanceOf(InvalidAdStatusException.class)
        .withMessage("Cannot reject a proposal for a non-available Ad");
    }

    @Test
    @DisplayName("When rejecting an unknown proposal, it throws an exception")
    void nonExistingProposal() throws InvalidAdStatusException, NonDecentProposalException {
      Ad cut = new Ad(musicianId, "Fender American Professional 2",
        new Price(BigDecimal.valueOf(1999.99), Currency.getInstance("EUR")));

      cut.makeProposal(anotherMusicianId, new Price(BigDecimal.valueOf(1799.99), Currency.getInstance("EUR")));

      assertThatException()
        .isThrownBy(() -> cut.rejectMusicianProposal(new MusicianId(UUID.randomUUID())))
        .isInstanceOf(UnknownAdProposalException.class)
        .withMessage("Cannot reject a proposal because it does not exist");
    }

    @Test
    @DisplayName("When rejecting a proposal, its status is updated")
    void success()
      throws InvalidAdStatusException, NonDecentProposalException, InvalidProposalStatusException, UnknownAdProposalException {
      Ad cut = new Ad(musicianId, "Fender American Professional 2",
        new Price(BigDecimal.valueOf(1999.99), Currency.getInstance("EUR")));

      cut.makeProposal(anotherMusicianId, new Price(BigDecimal.valueOf(1799.99), Currency.getInstance("EUR")));

      cut.rejectMusicianProposal(anotherMusicianId);

      List<Proposal> proposals = getMusicianProposals(cut, anotherMusicianId);

      assertThat(proposals).hasSize(1);
      assertThat(proposals.getFirst().getStatus()).isEqualTo(ProposalStatus.REJECTED);
    }
  }

  @Nested
  class AcceptProposal {

    @Test
    @DisplayName("When accepting a proposal for an non-available ad, it throws an exception")
    void invalidAdStatus() throws InvalidAdStatusException, NonDecentProposalException {
      Ad cut = new Ad(musicianId, "Fender American Professional 2",
        new Price(BigDecimal.valueOf(1999.99), Currency.getInstance("EUR")));

      cut.makeProposal(anotherMusicianId, new Price(BigDecimal.valueOf(1799.99), Currency.getInstance("EUR")));

      cut.sell();

      assertThatException()
        .isThrownBy(() -> cut.acceptMusicianProposal(anotherMusicianId))
        .isInstanceOf(InvalidAdStatusException.class)
        .withMessage("Cannot accept a proposal for a non-available Ad");
    }

    @Test
    @DisplayName("When accepting an unknown proposal, it throws an exception")
    void nonExistingProposal() throws InvalidAdStatusException, NonDecentProposalException {
      Ad cut = new Ad(musicianId, "Fender American Professional 2",
        new Price(BigDecimal.valueOf(1999.99), Currency.getInstance("EUR")));

      cut.makeProposal(anotherMusicianId, new Price(BigDecimal.valueOf(1799.99), Currency.getInstance("EUR")));

      assertThatException()
        .isThrownBy(() -> cut.acceptMusicianProposal(new MusicianId(UUID.randomUUID())))
        .isInstanceOf(UnknownAdProposalException.class)
        .withMessage("Cannot accept a proposal because it does not exist");
    }

    @Test
    @DisplayName("When accepting a proposal, the Ad is sold with the proposal price")
    void success()
      throws InvalidAdStatusException, NonDecentProposalException, InvalidProposalStatusException, UnknownAdProposalException {
      Ad cut = new Ad(musicianId, "Fender American Professional 2",
        new Price(BigDecimal.valueOf(1999.99), Currency.getInstance("EUR")));
      Price desiredPrice = new Price(BigDecimal.valueOf(1799.99), Currency.getInstance("EUR"));

      cut.makeProposal(anotherMusicianId, desiredPrice);

      cut.acceptMusicianProposal(anotherMusicianId);

      List<Proposal> proposals = getMusicianProposals(cut, anotherMusicianId);

      assertThat(proposals).hasSize(1);
      assertThat(proposals.getFirst().getStatus()).isEqualTo(ProposalStatus.ACCEPTED);
      assertThat(cut.getPrice()).isEqualTo(desiredPrice);
      assertThat(cut.getStatus()).isEqualTo(AdStatus.SOLD_OUT);
    }
  }
}
