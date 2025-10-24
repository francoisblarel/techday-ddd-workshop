package com.decathlon.techday.dddworkshop.marketplace.application.usecases;

import com.decathlon.techday.dddworkshop.marketplace.application.usecases.commands.PublishAdCommand;
import com.decathlon.techday.dddworkshop.marketplace.application.usecases.responses.PublishAdResponse;
import com.decathlon.techday.dddworkshop.marketplace.domain.AdRepository;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.Ad;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.AdFactory;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.MusicianAdsLimitReached;
import com.decathlon.techday.dddworkshop.musician.domain.MusicianRepository;
import com.decathlon.techday.dddworkshop.musician.domain.models.Musician;
import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class PublishAd {

  private final MusicianRepository musicianRepository;
  private final AdRepository adRepository;

  public PublishAd(MusicianRepository musicianRepository, AdRepository adRepository) {
    this.musicianRepository = musicianRepository;
    this.adRepository = adRepository;
  }

  public PublishAdResponse execute(PublishAdCommand command) throws MusicianAdsLimitReached {
    MusicianId musicianId = command.adCommand().musicianId();
    Optional<Musician> maybeMusician = musicianRepository.findById(musicianId);
    List<Ad> musicianAds = adRepository.findByMusicianId(musicianId);

    if (maybeMusician.isEmpty()) {
      throw new IllegalArgumentException("Unknown musician"); // TODO: exception in Marketplace context or Musician ?
    }

    Ad publishedAd = AdFactory.publishAd(command.adCommand(), musicianAds, maybeMusician.get());

    adRepository.save(publishedAd);

    return new PublishAdResponse(publishedAd.getId());
  }

}
