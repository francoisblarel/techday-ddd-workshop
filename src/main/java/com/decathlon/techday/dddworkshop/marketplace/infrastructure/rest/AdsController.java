package com.decathlon.techday.dddworkshop.marketplace.infrastructure.rest;

import com.decathlon.techday.dddworkshop.marketplace.application.queries.GetAd;
import com.decathlon.techday.dddworkshop.marketplace.application.queries.queries.GetAdQuery;
import com.decathlon.techday.dddworkshop.marketplace.application.queries.responses.GetAdResponse;
import com.decathlon.techday.dddworkshop.marketplace.application.usecases.AcceptAdProposal;
import com.decathlon.techday.dddworkshop.marketplace.application.usecases.MakeAdProposal;
import com.decathlon.techday.dddworkshop.marketplace.application.usecases.PublishAd;
import com.decathlon.techday.dddworkshop.marketplace.application.usecases.commands.AcceptAdProposalCommand;
import com.decathlon.techday.dddworkshop.marketplace.application.usecases.commands.AdProposalCommand;
import com.decathlon.techday.dddworkshop.marketplace.application.usecases.commands.PublishAdCommand;
import com.decathlon.techday.dddworkshop.marketplace.application.usecases.responses.PublishAdResponse;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.Ad;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.InvalidAdStatusException;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.InvalidProposalStatusException;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.MusicianAdsLimitReached;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.NonDecentProposalException;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.UnknownAdException;
import com.decathlon.techday.dddworkshop.marketplace.infrastructure.rest.dtos.AdResponseDto;
import com.decathlon.techday.dddworkshop.marketplace.infrastructure.rest.dtos.MakeAdProposalDto;
import com.decathlon.techday.dddworkshop.marketplace.infrastructure.rest.dtos.PublishAdDto;
import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AdsController.MARKETPLACE)
public class AdsController {

  public static final String MARKETPLACE = "/v1/marketplaces/ads";

  private final MakeAdProposal makeAdProposal;
  private final AcceptAdProposal acceptAdProposal;
  private final PublishAd publishAd;
  private final GetAd getAd;

  public AdsController(MakeAdProposal makeAdProposal, AcceptAdProposal acceptAdProposal, PublishAd publishAd,
    GetAd getAd) {
    this.makeAdProposal = makeAdProposal;
    this.acceptAdProposal = acceptAdProposal;
    this.publishAd = publishAd;
    this.getAd = getAd;
  }

  @GetMapping("/{id}")
  public ResponseEntity<AdResponseDto> getAdById(@PathVariable UUID id) {

    GetAdResponse adResponse = getAd.handle(new GetAdQuery(id));

    Optional<Ad> maybeAd = adResponse.maybeAd();

    return maybeAd
      .map(ad -> new ResponseEntity<>(new AdResponseDto(ad), HttpStatus.OK))
      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping()
  public ResponseEntity<UUID> publishAd(@RequestHeader("x-musician-id") UUID musicianId, @RequestBody PublishAdDto dto)
    throws MusicianAdsLimitReached {

    PublishAdResponse publishAdResponse = publishAd.execute(
      new PublishAdCommand(dto.toAdCommand(new MusicianId(musicianId))));

    return new ResponseEntity<>(publishAdResponse.id(), HttpStatus.CREATED);
  }

  @PostMapping("/{id}/proposals")
  public ResponseEntity<Object> makeAdProposal(@PathVariable UUID id, @RequestHeader("x-musician-id") UUID musicianId,
    @RequestBody
    MakeAdProposalDto dto) throws UnknownAdException, NonDecentProposalException, InvalidAdStatusException {

    makeAdProposal.execute(new AdProposalCommand(id, new MusicianId(musicianId), dto.getPrice().toPrice()));

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PostMapping("/{id}/proposals/musicians/{musicianId}/accept")
  public ResponseEntity<Object> acceptMusicianProposal(@PathVariable UUID id, @PathVariable UUID musicianId)
    throws UnknownAdException, InvalidAdStatusException, InvalidProposalStatusException {

    acceptAdProposal.execute(new AcceptAdProposalCommand(id, new MusicianId(musicianId)));

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  // TODO make ad discount
}
