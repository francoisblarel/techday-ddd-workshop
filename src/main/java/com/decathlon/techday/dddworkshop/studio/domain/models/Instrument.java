package com.decathlon.techday.dddworkshop.studio.domain.models;

import com.decathlon.techday.dddworkshop.studio.domain.models.exceptions.InvalidInstrumentStatusException;
import java.util.List;
import java.util.UUID;

public class Instrument {

  private static final int ALMOST_SOLD_OUT_QUANTITIES = 5;
  private final UUID id;
  private final UUID musicianId;
  private final String name;
  private final String description;
  private final List<Question> questions;
  private InstrumentStatus status;
  private Quantity quantity;
  private Price price;

  public Instrument(UUID musicianId, String name, String description, Price price) {
    this.musicianId = musicianId;
    this.id = UUID.randomUUID();
    this.name = name;
    this.description = description;
    this.price = price;
    this.status = InstrumentStatus.DRAFT;
    this.quantity = Quantity.NOT_AVAILABLE;
    this.questions = List.of();
  }

  public UUID id() {
    return id;
  }

  public Price price() {
    return price;
  }

  public String name() {
    return name;
  }

  public String description() {
    return description;
  }

  public InstrumentStatus status() {
    return status;
  }

  public Quantity quantity() {
    return quantity;
  }

  public boolean isPublished() {
    return status == InstrumentStatus.PUBLISHED;
  }

  public void publish(Quantity quantity) throws InvalidInstrumentStatusException {
    if (status != InstrumentStatus.DRAFT) {
      throw new InvalidInstrumentStatusException("Cannot publish a not DRAFT Instrument");
    }
    if (name.isEmpty()) {
      throw new IllegalStateException("Cannot publish an Instrument without name");
    }

    this.quantity = quantity;
    this.status = InstrumentStatus.PUBLISHED;
  }

  public void applyDiscount(float discount) {
    if (quantity.value() < ALMOST_SOLD_OUT_QUANTITIES) {
      throw new RuntimeException("Too much quantity to apply discount");
    }

    this.price = price.discount(discount);
  }

  public void sell(Quantity quantityToSell) {
    if (!status.equals(InstrumentStatus.PUBLISHED)) {
      throw new RuntimeException("Cannot sell items if Instrument is not published");
    }

    this.quantity = quantity.decrease(quantityToSell);

    if (quantity.isNotAvailable()) {
      this.status = InstrumentStatus.SOLD_OUT;
    }
  }

  public void askQuestion(Question question) throws InvalidInstrumentStatusException {
    if (!status.equals(InstrumentStatus.PUBLISHED)) {
      throw new InvalidInstrumentStatusException("Cannot ask questions if Instrument is not published");
    }

    questions.add(question);
  }

  public void answerQuestion(UUID questionId, Answer answer) throws InvalidInstrumentStatusException {
    if (!status.equals(InstrumentStatus.PUBLISHED)) {
      throw new InvalidInstrumentStatusException("Cannot answer question if Instrument is not published");
    }

    questions.stream()
      .filter(question -> question.getId().equals(questionId))
      .findFirst()
      .ifPresent(question -> question.answer(answer));
  }
}
