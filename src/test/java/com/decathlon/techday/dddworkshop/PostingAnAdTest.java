package com.decathlon.techday.dddworkshop;

import static com.decathlon.techday.dddworkshop.domain.AdStatus.DRAFT;
import static org.assertj.core.api.Assertions.assertThat;

import com.decathlon.techday.dddworkshop.application.PostingAnAd;
import com.decathlon.techday.dddworkshop.domain.Ad;
import java.util.Currency;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PostingAnAdTest {

  /**
   * App allowing to sell ads
   * Exercise: Make this test pass by implementing the Ad class
   * <p>
   * Un user peut poster une nouvelle annonce (avec un titre, une description, un prix et une currency)
   * L'annonce sera placée en Draft par défaut
   * On ne peut pas publier une annonce sans photo
   * On peut appliquer une réduction sur le prix de l'annonce une fois que celle ci est publiée
   * <p>
   * <p>
   * On veut faire une application permettant de vendre des instruments entre musiciens.
   * En tant que musicien je peux publier des annonces dans mon studio.
   * On ne peut pas publier plus de 3 annonces d'instruments par studio sans être premium.
   * Un musicien peut faire des réductions sur le prix des instruments qu'il vend.
   * Un musicien peut poser des questions sur une annonce.
   * Un musicien peut suivre ses annonces, ou un autre musicien pour être notifié des nouvelles annonces.
   * L'appli permet de visualiser le profil d'un musicien (nombre d'annonces, nombre de followers, ventes, achats, réputation, etc...)
   * On peut rechercher des annonces par instrument, studio, musicien, etc...
   * <p>
   * Step 1: event storming
   * Step 2: definitions
   * Step 3: implement the domain model to make the tests pass
   * <p>
   * Good architecture is the one which postpones all important decisions
   * <p>
   * IDEAs:
   * <p>
   * - type différent selon les états de l'annonce (DraftAd, PublishedAd, etc...)
   * - regles archunit
   *
   */

  @Test
  @DisplayName("A new ad should have the DRAFT status")
  void test1() {
    UUID adId = UUID.randomUUID();
    Ad ad = new Ad(adId, 10, Currency.getInstance("EUR"), "wooden cup", "a nice wooden cup",
      DRAFT, 1);

    assertThat(ad.status()).isEqualTo(DRAFT);
  }

  @Test
  @DisplayName("should apply discount to the ad price")
  void test() {
    UUID adId = UUID.randomUUID();
    Ad ad = new Ad(adId, 10, Currency.getInstance("EUR"), "wooden cup", "a nice wooden cup",
      DRAFT, 1);

    PostingAnAd adService = new PostingAnAd();
    adService.applyDiscount(adId, 10);

    assertThat(ad.price()).isEqualTo(9);
  }
}
