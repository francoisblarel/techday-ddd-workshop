package com.decathlon.techday.dddworkshop.marketplace.domain.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;

import com.decathlon.techday.dddworkshop.marketplace.domain.models.AdFactory.AdCommand;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.MusicianAdsLimitReached;
import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AdFactoryTest {

  @Test
  @DisplayName("When a non premium musician has reached is free limit threshold, he cannot publish a new ad")
  void nonPremiumMusician_limitReached() {
    MusicianId musicianId = new MusicianId(UUID.randomUUID());
    AdCommand adCommand = new AdCommand(musicianId, "Fender American Professional 2",
      new Price(BigDecimal.valueOf(1999.99), Currency.getInstance("EUR")));
    List<Ad> musicianAds = List.of(
      AdFixture.FENDER(musicianId),
      AdFixture.GIBSON(musicianId),
      AdFixture.IBANEZ(musicianId));

    assertThatException()
      .isThrownBy(() -> AdFactory.publishAd(adCommand, musicianAds, false))
      .isInstanceOf(MusicianAdsLimitReached.class)
      .withMessage("Free ads limit reached for non premium musician");
  }

  @Test
  @DisplayName("When a non premium musician has not reached is free limit threshold, he can publish a new ad")
  void nonPremiumMusician_noLimitReached() throws MusicianAdsLimitReached {
    MusicianId musicianId = new MusicianId(UUID.randomUUID());
    String instrument = "Fender American Professional 2";
    Price price = new Price(BigDecimal.valueOf(1999.99), Currency.getInstance("EUR"));
    AdCommand adCommand = new AdCommand(musicianId, instrument, price);
    List<Ad> musicianAds = List.of(AdFixture.FENDER(musicianId));

    Ad result = AdFactory.publishAd(adCommand, musicianAds, false);

    assertThat(result.getInstrument()).isEqualTo(instrument);
    assertThat(result.getMusicianId()).isEqualTo(musicianId);
    assertThat(result.getPrice()).isEqualTo(price);
  }

  @Test
  @DisplayName("When a musician is premium, he can publish an new ad")
  void premiumMusician() throws MusicianAdsLimitReached {
    MusicianId musicianId = new MusicianId(UUID.randomUUID());
    String instrument = "Fender American Professional 2";
    Price price = new Price(BigDecimal.valueOf(1999.99), Currency.getInstance("EUR"));
    AdCommand adCommand = new AdCommand(musicianId, instrument, price);
    List<Ad> musicianAds = List.of(
      AdFixture.FENDER(musicianId),
      AdFixture.GIBSON(musicianId),
      AdFixture.IBANEZ(musicianId));
    Ad result = AdFactory.publishAd(adCommand, musicianAds, true);

    assertThat(result.getInstrument()).isEqualTo(instrument);
    assertThat(result.getMusicianId()).isEqualTo(musicianId);
    assertThat(result.getPrice()).isEqualTo(price);
  }
}
