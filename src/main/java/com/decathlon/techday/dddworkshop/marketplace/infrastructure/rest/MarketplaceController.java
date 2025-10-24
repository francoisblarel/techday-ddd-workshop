package com.decathlon.techday.dddworkshop.marketplace.infrastructure.rest;

import com.decathlon.techday.dddworkshop.marketplace.application.usecases.MakeAdProposal;
import com.decathlon.techday.dddworkshop.marketplace.application.usecases.commands.AdProposalCommand;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.InvalidAdStatusException;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.NonDecentProposalException;
import com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions.UnknownAdException;
import com.decathlon.techday.dddworkshop.marketplace.infrastructure.rest.dtos.MakeAdProposalDto;
import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MarketplaceController.MARKETPLACE)
public class MarketplaceController {

  public static final String MARKETPLACE = "/v1/marketplaces";

  private final MakeAdProposal makeAdProposal;

  public MarketplaceController(MakeAdProposal makeAdProposal) {
    this.makeAdProposal = makeAdProposal;
  }

  @PostMapping("/ads/{id}/proposals")
  public ResponseEntity<Object> makeAdProposal(@PathVariable UUID id, @RequestHeader("x-musician-id") UUID musicianId,
    @RequestBody
    MakeAdProposalDto dto) throws UnknownAdException, NonDecentProposalException, InvalidAdStatusException {

    makeAdProposal.execute(new AdProposalCommand(id, new MusicianId(musicianId), dto.toPrice()));

    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
