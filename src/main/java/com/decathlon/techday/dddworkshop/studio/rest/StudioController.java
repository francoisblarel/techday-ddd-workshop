package com.decathlon.techday.dddworkshop.studio.rest;

import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import com.decathlon.techday.dddworkshop.studio.domain.InstrumentRepository;
import com.decathlon.techday.dddworkshop.studio.rest.dtos.InstrumentDto;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(StudioController.STUDIO)
public class StudioController {

  public static final String STUDIO = "/v1/studios";

  private final InstrumentRepository instrumentRepository;

  public StudioController(InstrumentRepository instrumentRepository) {
    this.instrumentRepository = instrumentRepository;
  }

  @GetMapping("/musicians/{id}")
  public ResponseEntity<List<InstrumentDto>> getMusicianStudio(@PathVariable UUID id) {
    List<InstrumentDto> instrumentDtos = instrumentRepository.getByMusician(new MusicianId(id)) // MOVE IT INTO SERVICE
      .stream()
      .map(InstrumentDto::new)
      .toList();

    return ResponseEntity.ok(instrumentDtos);
  }
}
