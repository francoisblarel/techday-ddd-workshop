package com.decathlon.techday.dddworkshop.marketplace.domain.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;

import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.InvalidAdStatusException;
import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import java.util.Currency;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class AdTest {

  private final Ad cut = new Ad(new MusicianId(UUID.randomUUID()), "Fender American Professional 2",
    new Price(1999.99f, Currency.getInstance("EUR")));

  @Test
  @DisplayName("When creating an Ad, the status is Available")
  void constructor() {
    assertThat(cut.getStatus()).isEqualTo(AdStatus.AVAILABLE);
  }

  @Nested
  class Sell {

    @Test
    @DisplayName("When successfully selling an Ad, it is now SOLD_OUT")
    void success() throws InvalidAdStatusException {
      cut.sell();

      assertThat(cut.getStatus()).isEqualTo(AdStatus.SOLD_OUT);
    }

    @Test
    @DisplayName("When selling a non-available Ad, it throws an exception")
    void nonAvailableAd() throws InvalidAdStatusException {
      cut.sell();

      assertThatException()
        .isThrownBy(cut::sell)
        .isInstanceOf(InvalidAdStatusException.class)
        .withMessageContaining("Cannot sell a non-available Ad");
    }

  }
}
