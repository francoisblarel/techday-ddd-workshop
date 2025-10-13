package com.decathlon.techday.dddworkshop.musician.infrastructure.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.decathlon.techday.dddworkshop.marketplace.domain.models.Price;
import com.decathlon.techday.dddworkshop.musician.application.queries.ViewMusicianProfile;
import com.decathlon.techday.dddworkshop.musician.application.queries.views.MusicianProfile;
import com.decathlon.techday.dddworkshop.musician.application.queries.views.MusicianProfile.InstrumentView;
import com.decathlon.techday.dddworkshop.musician.domain.models.Reputation;
import com.decathlon.techday.dddworkshop.musician.infrastructure.rest.dtos.MusicianProfileDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Currency;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {MusicianController.class,})
class MusicianControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  @MockitoBean
  private ViewMusicianProfile viewMusicianProfile;

  @Nested
  class GetMusicianProfile {

    @Test
    @DisplayName("When an error occurred while getting musician profile, it returns a bad request")
    void get_failure() throws Exception {
      UUID musicianId = UUID.randomUUID();
      String uri = "/v1/musicians/%s/profile".formatted(musicianId);
      when(viewMusicianProfile.viewProfile(musicianId)).thenThrow(new RuntimeException("error description"));
      mockMvc.perform(MockMvcRequestBuilders
          .get(uri)
          .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
        )
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.path").value(uri))
        .andExpect(jsonPath("$.timestamp").exists())
        .andExpect(jsonPath("$.error").value("Bad Request"))
        .andExpect(jsonPath("$.status").value("400"))
        .andExpect(jsonPath("$.error_description").value("error description"));
    }

    @Test
    @DisplayName("When successfully getting profile, it returns it")
    void get_success() throws Exception {
      UUID musicianId = UUID.randomUUID();
      String uri = "/v1/musicians/%s/profile".formatted(musicianId);
      MusicianProfile musicianProfile = new MusicianProfile("Bob", Reputation.AMBASSADOR, List.of(
        new InstrumentView("Fender American 2", "Awesome guitar", new Price(2000, Currency.getInstance("EUR")))),
        List.of());
      MusicianProfileDto expected = new MusicianProfileDto(musicianProfile);

      when(viewMusicianProfile.viewProfile(musicianId)).thenReturn(musicianProfile);

      MvcResult response = mockMvc.perform(MockMvcRequestBuilders
          .get(uri)
          .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
        )
        .andExpect(status().isOk())
        .andReturn();

      MusicianProfileDto result = objectMapper.readValue(response.getResponse().getContentAsString(),
        MusicianProfileDto.class);
      assertThat(result).isEqualTo(expected);
    }
  }
}
