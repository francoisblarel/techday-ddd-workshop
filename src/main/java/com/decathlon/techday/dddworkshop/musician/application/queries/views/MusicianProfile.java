package com.decathlon.techday.dddworkshop.musician.application.queries.views;

import com.decathlon.techday.dddworkshop.musician.domain.models.Reputation;
import com.decathlon.techday.dddworkshop.studio.domain.models.Price;
import java.util.List;

public record MusicianProfile(
  String name,
  Reputation reputation,
  List<InstrumentView> sells,
  List<InstrumentView> buyingHistory
) {

  record InstrumentView(String name, String description, Price price) {

  }
}
