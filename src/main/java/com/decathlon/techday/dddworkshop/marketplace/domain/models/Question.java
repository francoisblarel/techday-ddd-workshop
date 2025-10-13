package com.decathlon.techday.dddworkshop.marketplace.domain.models;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class Question {

  private final UUID id;
  private final String text;
  private final UUID userId;
  private final Instant date;
  private final List<Answer> answers;

  public Question(String text, UUID userId) {
    this.id = UUID.randomUUID();
    this.text = text;
    this.userId = userId;
    this.date = Instant.now();
    this.answers = List.of();
  }

  public UUID getId() {
    return id;
  }

  public String getText() {
    return text;
  }

  public UUID getUserId() {
    return userId;
  }

  public Instant getDate() {
    return date;
  }

  public void answer(Answer answer) {
    this.answers.add(answer);
  }
}
