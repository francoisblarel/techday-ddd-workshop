package com.decathlon.techday.dddworkshop.marketplace.domain.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;
import static org.mockito.Mockito.when;

import com.decathlon.techday.dddworkshop.fixtures.InstrumentFixture;
import com.decathlon.techday.dddworkshop.marketplace.domain.InstrumentRepository;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.Instrument;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.Quantity;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.InstrumentLimitReachedException;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.InvalidInstrumentException;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.InvalidInstrumentStatusException;
import com.decathlon.techday.dddworkshop.musician.domain.models.Musician;
import java.util.List;
import java.util.UUID;
import net.jqwik.api.Label;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudioTest {

  @InjectMocks
  private Studio cut;
  @Mock
  private InstrumentRepository instrumentRepository;

  @Test
  @Label("When Musician has more than 3 published Instruments, no more InstrumentDbEntity can be published")
  void should_not_publish_instrument() {
    Musician musician = new Musician("Bob");
    UUID userId = musician.getId();

    when(instrumentRepository.getAllByUser(userId)).thenReturn(List.of(
      InstrumentFixture.PUBLISHED_SPECTOR_BASS(userId, 3),
      InstrumentFixture.PUBLISHED_SPECTOR_BASS(userId, 5),
      InstrumentFixture.PUBLISHED_SPECTOR_BASS(userId, 2)
    ));

    Instrument instrumentToPublish = InstrumentFixture.DRAFT_SPECTOR_BASS(userId);

    assertThatException()
      .isThrownBy(() -> cut.publishInstrument(musician, instrumentToPublish, new Quantity(2)))
      .isInstanceOf(InstrumentLimitReachedException.class)
      .withMessage("Musician published Instruments limit reached");
  }

  @Test
  @Label("When Musician has less than 3 published Instruments, the InstrumentDbEntity can be published")
  void should_publish_instrument()
    throws InvalidInstrumentStatusException, InstrumentLimitReachedException, InvalidInstrumentException {
    Musician musician = new Musician("Bob");
    UUID userId = musician.getId();
    Instrument instrumentToPublish = InstrumentFixture.DRAFT_SPECTOR_BASS(userId);

    when(instrumentRepository.getAllByUser(userId)).thenReturn(List.of(
      InstrumentFixture.DRAFT_SPECTOR_BASS(userId),
      InstrumentFixture.DRAFT_SPECTOR_BASS(userId),
      InstrumentFixture.PUBLISHED_SPECTOR_BASS(userId, 5),
      InstrumentFixture.PUBLISHED_SPECTOR_BASS(userId, 2)
    ));

    Instrument publishedInstrument = cut.publishInstrument(musician, instrumentToPublish, new Quantity(2));

    assertThat(publishedInstrument.isPublished()).isTrue();
    assertThat(publishedInstrument.quantity()).isEqualTo(new Quantity(2));
  }
}
