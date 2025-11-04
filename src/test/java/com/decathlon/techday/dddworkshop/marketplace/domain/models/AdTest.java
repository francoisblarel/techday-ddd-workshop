package com.decathlon.techday.dddworkshop.marketplace.domain.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;

import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.InvalidAdStatusException;
import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class AdTest {

  private final MusicianId musicianId = new MusicianId(UUID.randomUUID());

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
}
