package com.decathlon.techday.dddworkshop.marketplace.domain.models;

import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.MusicianAdsLimitReached;
import com.decathlon.techday.dddworkshop.musician.domain.models.Musician;
import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import java.util.List;

public class AdFactory {

  public static final int FREE_ADS_THRESHOLDS = 3;

  public static Ad publishAd(AdCommand adCommand, List<Ad> musicianAds, Musician musician)
    throws MusicianAdsLimitReached {
    if (musicianAds.size() >= FREE_ADS_THRESHOLDS && !musician.isPremium()) {
      throw new MusicianAdsLimitReached("Free ads limit reached for non premium musician");
    }

    return new Ad(adCommand.musicianId(), adCommand.instrument(), adCommand.price());

  }

  public record AdCommand(MusicianId musicianId, String instrument, Price price) {

  }
}
