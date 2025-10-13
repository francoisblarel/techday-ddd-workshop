/*
package com.decathlon.techday.dddworkshop.musician.infrastructure.rest;

import com.decathlon.techday.dddworkshop.musician.application.queries.ViewMusicianProfile;
import com.decathlon.techday.dddworkshop.musician.application.queries.views.MusicianProfile;
import com.decathlon.techday.dddworkshop.musician.infrastructure.rest.dtos.MusicianProfileDto;
import java.util.Date;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping(MusicianController.MUSICIANS)
public class MusicianController {

  public static final String MUSICIANS = "/v1/musicians";

  private final ViewMusicianProfile viewMusicianProfile;

  public MusicianController(ViewMusicianProfile viewMusicianProfile) {
    this.viewMusicianProfile = viewMusicianProfile;
  }

  @GetMapping("/{id}/profile")
  public ResponseEntity<MusicianProfileDto> getProfile(@PathVariable UUID id) {
    MusicianProfile musicianProfile = viewMusicianProfile.viewProfile(id);

    return ResponseEntity.ok(new MusicianProfileDto(musicianProfile));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception exception, WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse(
      new Date(),
      ((ServletWebRequest) request).getRequest().getRequestURI(),
      HttpStatus.BAD_REQUEST.value(),
      HttpStatus.BAD_REQUEST.getReasonPhrase(),
      exception.getMessage()
    );
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

}
*/
