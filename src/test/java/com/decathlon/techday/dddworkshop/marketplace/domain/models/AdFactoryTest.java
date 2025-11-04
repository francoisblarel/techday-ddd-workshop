package com.decathlon.techday.dddworkshop.marketplace.domain.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;

import com.decathlon.techday.dddworkshop.marketplace.domain.models.AdFactory.AdCommand;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.MusicianAdsLimitReached;
import com.decathlon.techday.dddworkshop.musician.domain.models.Musician;
import com.decathlon.techday.dddworkshop.musician.domain.models.MusicianFixture;
import java.util.Currency;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AdFactoryTest {

  @Test
  @DisplayName("When a non premium musician has reached is free limit threshold, he cannot publish a new ad")
  void nonPremiumMusician_limitReached() {
    Musician newMusician = MusicianFixture.NewMusician();
    AdCommand adCommand = new AdCommand(newMusician.getId(), "Fender American Professional 2",
      new Price(1999.99f, Currency.getInstance("EUR")));
    List<Ad> musicianAds = List.of(
      AdFixture.FENDER(newMusician.getId()),
      AdFixture.GIBSON(newMusician.getId()),
      AdFixture.IBANEZ(newMusician.getId()));

    assertThatException()
      .isThrownBy(() -> AdFactory.publishAd(adCommand, musicianAds, newMusician.isPremium()))
      .isInstanceOf(MusicianAdsLimitReached.class)
      .withMessage("Free ads limit reached for non premium musician");
  }

  @Test
  @DisplayName("When a non premium musician has not reached is free limit threshold, he can publish a new ad")
  void nonPremiumMusician_noLimitReached() throws MusicianAdsLimitReached {
    Musician newMusician = MusicianFixture.NewMusician();
    String instrument = "Fender American Professional 2";
    Price price = new Price(1999.99f, Currency.getInstance("EUR"));
    AdCommand adCommand = new AdCommand(newMusician.getId(), instrument, price);
    List<Ad> musicianAds = List.of(AdFixture.FENDER(newMusician.getId()));

    Ad result = AdFactory.publishAd(adCommand, musicianAds, newMusician.isPremium());

    assertThat(result.getInstrument()).isEqualTo(instrument);
    assertThat(result.getMusicianId()).isEqualTo(newMusician.getId());
    assertThat(result.getPrice()).isEqualTo(price);
  }

  @Test
  @DisplayName("When a musician is premium, he can publish an new ad")
  void premiumMusician() throws MusicianAdsLimitReached {
    Musician premiumMusician = MusicianFixture.PremiumMusician();
    String instrument = "Fender American Professional 2";
    Price price = new Price(1999.99f, Currency.getInstance("EUR"));
    AdCommand adCommand = new AdCommand(premiumMusician.getId(), instrument, price);
    List<Ad> musicianAds = List.of(
      AdFixture.FENDER(premiumMusician.getId()),
      AdFixture.GIBSON(premiumMusician.getId()),
      AdFixture.IBANEZ(premiumMusician.getId()));
    Ad result = AdFactory.publishAd(adCommand, musicianAds, premiumMusician.isPremium());

    assertThat(result.getInstrument()).isEqualTo(instrument);
    assertThat(result.getMusicianId()).isEqualTo(premiumMusician.getId());
    assertThat(result.getPrice()).isEqualTo(price);
  }
}
