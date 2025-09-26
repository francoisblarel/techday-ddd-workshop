package com.decathlon.techday.dddworkshop.musician.infrastructure.rest;

import static com.decathlon.techday.dddworkshop.musician.infrastructure.rest.MusicianController.MUSICIANS;

import com.decathlon.techday.dddworkshop.musician.application.queries.ViewMusicianProfile;
import com.decathlon.techday.dddworkshop.musician.infrastructure.rest.dtos.MusicianProfileDto;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController(MUSICIANS)
public class MusicianController {

  public static final String MUSICIANS = "/musicians";

  private ViewMusicianProfile viewMusicianProfile;

  @GetMapping("/{id}/profile")
  public ResponseEntity<MusicianProfileDto> getProfile(@PathVariable UUID id) {
    return ResponseEntity.ok(new MusicianProfileDto(viewMusicianProfile.viewProfile(id)));
  }
}
