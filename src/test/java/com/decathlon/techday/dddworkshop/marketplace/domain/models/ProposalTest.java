package com.decathlon.techday.dddworkshop.marketplace.domain.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;

import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.NonDecentProposalException;
import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import java.util.Currency;
import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ProposalTest {

  @Nested
  class MakeProposal {

    @Test
    void non_decent_proposal() {
      MusicianId musicianId = new MusicianId(UUID.randomUUID());
      Price originalPrice = new Price(150.00f, Currency.getInstance("EUR"));
      Price desiredPrice = new Price(80.00f, Currency.getInstance("EUR"));

      assertThatException()
        .isThrownBy(() -> Proposal.makeProposal(musicianId, desiredPrice, originalPrice))
        .isInstanceOf(NonDecentProposalException.class)
        .withMessage("Proposal must be decent!");
    }

    @Test
    void success() throws NonDecentProposalException {
      MusicianId musicianId = new MusicianId(UUID.randomUUID());
      Price originalPrice = new Price(150.00f, Currency.getInstance("EUR"));
      Price desiredPrice = new Price(120.00f, Currency.getInstance("EUR"));

      Proposal result = Proposal.makeProposal(musicianId, desiredPrice, originalPrice);

      assertThat(result.getMusicianId()).isEqualTo(musicianId);
      assertThat(result.getDesiredPrice()).isEqualTo(desiredPrice);
      assertThat(result.getStatus()).isEqualTo(ProposalStatus.WAITING);
    }
  }
}
