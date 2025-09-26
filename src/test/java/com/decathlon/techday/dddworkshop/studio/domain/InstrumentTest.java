package com.decathlon.techday.dddworkshop.studio.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatRuntimeException;

import com.decathlon.techday.dddworkshop.fixtures.InstrumentFixture;
import com.decathlon.techday.dddworkshop.studio.domain.models.Instrument;
import com.decathlon.techday.dddworkshop.studio.domain.models.InstrumentStatus;
import com.decathlon.techday.dddworkshop.studio.domain.models.Price;
import com.decathlon.techday.dddworkshop.studio.domain.models.Quantity;
import com.decathlon.techday.dddworkshop.studio.domain.models.exceptions.InvalidInstrumentStatusException;
import java.util.Currency;
import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InstrumentTest {

  // TODO Publish + Constructor

  @Nested
  class Discount {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4})
    void almostSoldOut(int quantity) {
      Instrument cut = InstrumentFixture.PUBLISHED_SPECTOR_BASS(UUID.randomUUID(), quantity);

      assertThatRuntimeException().isThrownBy(() -> cut.applyDiscount(10))
        .withMessageStartingWith("Too much quantity to apply discount");
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 20})
    void success(int quantity) {
      Instrument cut = InstrumentFixture.PUBLISHED_SPECTOR_BASS(UUID.randomUUID(), quantity);
      Price originalPrice = cut.price();
      float discountPercentage = 10f;

      cut.applyDiscount(discountPercentage);

      assertThat(cut.price()).isEqualTo(originalPrice.discount(discountPercentage));
    }
  }

  @Nested
  class Sell {

    @Test
    void notPublished() {
      Instrument cut = InstrumentFixture.DRAFT_SPECTOR_BASS(UUID.randomUUID());

      assertThatRuntimeException()
        .isThrownBy(() -> cut.sell(new Quantity(2)))
        .withMessageStartingWith("Cannot sell items if Instrument is not published");
    }

    @Test
    void remainingStock() {
      Instrument cut = InstrumentFixture.PUBLISHED_SPECTOR_BASS(UUID.randomUUID(), 10);
      Quantity originalQuantity = cut.quantity();
      Quantity quantityToSell = new Quantity(2);

      cut.sell(quantityToSell);

      assertThat(cut.quantity()).isEqualTo(originalQuantity.decrease(quantityToSell));
      assertThat(cut.status()).isEqualTo(InstrumentStatus.PUBLISHED);
    }

    @Test
    void noMoreStock() {
      Instrument cut = InstrumentFixture.PUBLISHED_SPECTOR_BASS(UUID.randomUUID(), 2);
      Quantity quantityToSell = new Quantity(2);

      cut.sell(quantityToSell);

      assertThat(cut.quantity()).isEqualTo(Quantity.NOT_AVAILABLE);
      assertThat(cut.status()).isEqualTo(InstrumentStatus.SOLD_OUT);
    }
  }

  @Nested
  class Properties {

    @Test
    void aPublishedAdHasAQuantity() throws InvalidInstrumentStatusException {
      Instrument cut = new Instrument(UUID.randomUUID(), "Wooden cup", "Amazing hand crafted cup",
        new Price(10.00f, Currency.getInstance("EUR")));

      cut.publish(new Quantity(5));
      cut.sell(new Quantity(3));

      assertThat(cut.status()).isEqualTo(InstrumentStatus.PUBLISHED);
      assertThat(cut.quantity().value()).isPositive();
    }

    @Test
    void aSoldOutAdHasANoQuantity() throws InvalidInstrumentStatusException {
      Instrument cut = new Instrument(UUID.randomUUID(), "Wooden cup", "Amazing hand crafted cup",
        new Price(10.00f, Currency.getInstance("EUR")));

      cut.publish(new Quantity(5));
      cut.sell(new Quantity(5));

      assertThat(cut.status()).isEqualTo(InstrumentStatus.SOLD_OUT);
      assertThat(cut.quantity()).isEqualTo(Quantity.NOT_AVAILABLE);
    }
  }
}
