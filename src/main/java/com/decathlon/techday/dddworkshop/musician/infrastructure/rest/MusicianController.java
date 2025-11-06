package com.decathlon.techday.dddworkshop.musician.infrastructure.rest;

import com.decathlon.techday.dddworkshop.musician.domain.MusicianRepository;
import com.decathlon.techday.dddworkshop.musician.infrastructure.rest.dtos.MusicianResponseDto;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MusicianController.MUSICIAN)
public class MusicianController {

  public static final String MUSICIAN = "/v1/musicians";

  private final MusicianRepository musicianRepository;

  public MusicianController(MusicianRepository musicianRepository) {
    this.musicianRepository = musicianRepository;
  }

  @GetMapping
  public ResponseEntity<List<MusicianResponseDto>> getAllMusicians() {
    List<MusicianResponseDto> musicianDtos = musicianRepository.findAll()
      .stream()
      .map(MusicianResponseDto::new)
      .toList();

    return ResponseEntity.ok(musicianDtos);
  }
}
