package com.decathlon.techday.dddworkshop.studio.domain;

import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import java.util.Currency;
import java.util.UUID;

public record Instrument(UUID id, MusicianId musicianId, String name, float price, Currency currency) {

}
