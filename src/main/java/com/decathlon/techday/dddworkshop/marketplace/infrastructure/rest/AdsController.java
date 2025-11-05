package com.decathlon.techday.dddworkshop.marketplace.infrastructure.rest;

import com.decathlon.techday.dddworkshop.marketplace.application.usecases.PublishAd;
import com.decathlon.techday.dddworkshop.marketplace.application.usecases.commands.PublishAdCommand;
import com.decathlon.techday.dddworkshop.marketplace.application.usecases.responses.PublishAdResponse;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.MusicianAdsLimitReached;
import com.decathlon.techday.dddworkshop.marketplace.infrastructure.rest.dtos.PublishAdDto;
import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import com.decathlon.techday.dddworkshop.shared.domain.expections.UnknownMusicianException;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AdsController.MARKETPLACE)
public class AdsController {

  public static final String MARKETPLACE = "/v1/marketplaces/ads";
  private static final String MUSICIAN_ID_HEADER = "x-musician-id";

  private final PublishAd publishAd;

  public AdsController(PublishAd publishAd) {
    this.publishAd = publishAd;
  }

  @PostMapping()
  public ResponseEntity<UUID> publishAd(@RequestHeader(MUSICIAN_ID_HEADER) UUID musicianId,
    @RequestBody PublishAdDto dto)
    throws MusicianAdsLimitReached, UnknownMusicianException {

    PublishAdResponse publishAdResponse = publishAd.execute(
      new PublishAdCommand(dto.toAdCommand(new MusicianId(musicianId))));

    return new ResponseEntity<>(publishAdResponse.id(), HttpStatus.CREATED);
  }

}
