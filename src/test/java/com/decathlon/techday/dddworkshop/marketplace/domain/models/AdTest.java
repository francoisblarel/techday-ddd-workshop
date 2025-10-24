package com.decathlon.techday.dddworkshop.marketplace.domain.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;

import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.InvalidAdStatusException;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.NonDecentProposalException;
import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import java.util.Currency;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class AdTest {

  private final MusicianId musicianId = new MusicianId(UUID.randomUUID());
  private final MusicianId anotherMusicianId = new MusicianId(UUID.randomUUID());

  @Test
  @DisplayName("When creating an Ad, the status is Available")
  void constructor() {
    Ad cut = new Ad(musicianId, "Fender American Professional 2", new Price(1999.99f, Currency.getInstance("EUR")));

    assertThat(cut.getStatus()).isEqualTo(AdStatus.AVAILABLE);
  }

  @Nested
  class Sell {

    @Test
    @DisplayName("When successfully selling an Ad, it is now SOLD_OUT")
    void success() throws InvalidAdStatusException {
      Ad cut = new Ad(musicianId, "Fender American Professional 2", new Price(1999.99f, Currency.getInstance("EUR")));

      cut.sell();

      assertThat(cut.getStatus()).isEqualTo(AdStatus.SOLD_OUT);
    }

    @Test
    @DisplayName("When selling a non-available Ad, it throws an exception")
    void nonAvailableAd() throws InvalidAdStatusException {
      Ad cut = new Ad(musicianId, "Fender American Professional 2", new Price(1999.99f, Currency.getInstance("EUR")));

      cut.sell();

      assertThatException()
        .isThrownBy(cut::sell)
        .isInstanceOf(InvalidAdStatusException.class)
        .withMessageContaining("Cannot sell a non-available Ad");
    }

  }

  @Nested
  class DoProposal {

    private static List<Proposal> getMusicianProposals(Ad cut, MusicianId musicianId1) {
      return cut.getProposals().stream()
        .filter(proposal -> proposal.getMusicianId().equals(musicianId1))
        .toList();
    }

    @Test
    void non_available_ad() throws InvalidAdStatusException {
      Ad cut = new Ad(musicianId, "Fender American Professional 2", new Price(1999.99f, Currency.getInstance("EUR")));

      cut.sell();

      assertThatException()
        .isThrownBy(() -> cut.doProposal(musicianId,
          new Price(1799.99f, Currency.getInstance("EUR"))))
        .isInstanceOf(InvalidAdStatusException.class)
        .withMessage("Cannot make a proposal for a non-available Ad");
    }

    @Test
    void non_existing_musician_proposal() throws NonDecentProposalException, InvalidAdStatusException {
      Ad cut = new Ad(musicianId, "Fender American Professional 2", new Price(1999.99f, Currency.getInstance("EUR")));

      cut.doProposal(musicianId, new Price(1799.99f, Currency.getInstance("EUR")));

      assertThat(getMusicianProposals(cut, musicianId)).hasSize(1);
    }

    @Test
    void existing_musician_proposal() throws NonDecentProposalException, InvalidAdStatusException {
      Ad cut = new Ad(musicianId, "Fender American Professional 2", new Price(1999.99f, Currency.getInstance("EUR")));
      cut.doProposal(anotherMusicianId, new Price(1895.00f, Currency.getInstance("EUR")));
      cut.doProposal(musicianId, new Price(1650.00f, Currency.getInstance("EUR")));

      cut.doProposal(musicianId, new Price(1799.99f, Currency.getInstance("EUR")));

      assertThat(getMusicianProposals(cut, musicianId)).hasSize(1);
    }
  }
}
