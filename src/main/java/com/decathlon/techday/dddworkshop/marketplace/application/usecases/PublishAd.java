package com.decathlon.techday.dddworkshop.marketplace.application.usecases;

import com.decathlon.techday.dddworkshop.marketplace.application.usecases.commands.PublishAdCommand;
import com.decathlon.techday.dddworkshop.marketplace.application.usecases.responses.PublishAdResponse;
import com.decathlon.techday.dddworkshop.marketplace.domain.AdRepository;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.Ad;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.AdFactory;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.MusicianAdsLimitReached;
import com.decathlon.techday.dddworkshop.musician.application.services.MusicianPort;
import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import com.decathlon.techday.dddworkshop.shared.domain.expections.UnknownMusicianException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PublishAd {

  private final MusicianPort musicianPort;
  private final AdRepository adRepository;

  public PublishAd(MusicianPort musicianPort, AdRepository adRepository) {
    this.musicianPort = musicianPort;
    this.adRepository = adRepository;
  }

  public PublishAdResponse execute(PublishAdCommand command) throws MusicianAdsLimitReached, UnknownMusicianException {
    MusicianId musicianId = command.adCommand().musicianId();
    boolean isPremiumMusician = musicianPort.isPremium(musicianId);
    List<Ad> musicianAds = adRepository.findByMusicianId(musicianId);

    Ad publishedAd = AdFactory.publishAd(command.adCommand(), musicianAds, isPremiumMusician);

    adRepository.save(publishedAd);

    return new PublishAdResponse(publishedAd.getId());
  }

}
