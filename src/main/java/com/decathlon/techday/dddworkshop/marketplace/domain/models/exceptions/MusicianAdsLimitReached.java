package com.decathlon.techday.dddworkshop.marketplace.domain.models.exceptions;

public class MusicianAdsLimitReached extends Exception {

  public MusicianAdsLimitReached(String message) {
    super(message);
  }
}
