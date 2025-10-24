package com.decathlon.techday.dddworkshop.marketplace.application.usecases;

import static org.assertj.core.api.Assertions.assertThatException;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.decathlon.techday.dddworkshop.marketplace.application.usecases.commands.AdProposalCommand;
import com.decathlon.techday.dddworkshop.marketplace.domain.AdRepository;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.Ad;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.Price;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.InvalidAdStatusException;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.NonDecentProposalException;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.UnknownAdException;
import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import java.util.Currency;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MakeAdProposalTest {

  @InjectMocks
  private MakeAdProposal cut;
  @Mock
  private AdRepository adRepository;
  @Mock
  private Ad ad;

  @Test
  void unknown_ad() {
    UUID adId = UUID.randomUUID();
    AdProposalCommand command = new AdProposalCommand(
      adId, new MusicianId(UUID.randomUUID()), new Price(1500.00f, Currency.getInstance("EUR")));
    when(adRepository.findById(any())).thenReturn(Optional.empty());

    assertThatException()
      .isThrownBy(() -> cut.execute(command))
      .isInstanceOf(UnknownAdException.class)
      .withMessage("Unknown ad " + adId);
  }

  @Test
  void ad_proposal_exception() throws NonDecentProposalException, InvalidAdStatusException {
    UUID adId = UUID.randomUUID();
    InvalidAdStatusException exception = new InvalidAdStatusException("error");
    AdProposalCommand command = new AdProposalCommand(adId, new MusicianId(UUID.randomUUID()),
      new Price(1500.00f, Currency.getInstance("EUR")));
    doThrow(exception).when(ad).makeProposal(any(), any());
    when(adRepository.findById(adId)).thenReturn(Optional.of(ad));

    assertThatException()
      .isThrownBy(() -> cut.execute(command))
      .isEqualTo(exception);

    verify(adRepository, never()).save(any());
  }

  @Test
  void success() throws NonDecentProposalException, InvalidAdStatusException {
    UUID adId = UUID.randomUUID();
    MusicianId musicianId = new MusicianId(UUID.randomUUID());
    Price desiredPrice = new Price(1500.00f, Currency.getInstance("EUR"));
    AdProposalCommand command = new AdProposalCommand(adId, musicianId,
      desiredPrice);
    doNothing().when(ad).makeProposal(musicianId, desiredPrice);
    when(adRepository.findById(adId)).thenReturn(Optional.of(ad));
    doNothing().when(adRepository).save(ad);

    assertThatNoException()
      .isThrownBy(() -> cut.execute(command));
  }
}
