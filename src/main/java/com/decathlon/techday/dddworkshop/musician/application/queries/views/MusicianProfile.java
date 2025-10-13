package com.decathlon.techday.dddworkshop.musician.application.queries.views;

import com.decathlon.techday.dddworkshop.marketplace.domain.models.Price;
import com.decathlon.techday.dddworkshop.musician.domain.models.Reputation;
import java.util.List;

public record MusicianProfile(
  String name,
  Reputation reputation,
  List<InstrumentView> sells,
  List<InstrumentView> buyingHistory
) {

  public record InstrumentView(String name, String description, Price price) {

  }
}
