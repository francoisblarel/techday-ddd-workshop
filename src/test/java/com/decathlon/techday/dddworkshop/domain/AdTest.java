package com.decathlon.techday.dddworkshop.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatRuntimeException;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.decathlon.techday.dddworkshop.fixtures.AdFixture;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AdTest {

  @Nested
  class Discount {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4})
    void almostSoldOut(int quantity) {
      Ad cut = AdFixture.WOODEN_CUP_AD_WITH_QUANTITY(quantity);

      assertThatRuntimeException().isThrownBy(() -> cut.applyDiscount(10))
        .withMessageStartingWith("Too much quantity to apply discount");
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 20})
    void success(int quantity) {
      Ad cut = AdFixture.WOODEN_CUP_AD_WITH_QUANTITY(quantity);
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
      Ad cut = AdFixture.DRAFT_WOODEN_CUP_AD();

      assertThatRuntimeException()
        .isThrownBy(() -> cut.sell(new Quantity(2)))
        .withMessageStartingWith("Cannot sell items if Ad is not published");
    }

    @Test
    void remainingStock() {
      Ad cut = AdFixture.WOODEN_CUP_AD_WITH_QUANTITY(10);
      Quantity originalQuantity = cut.quantity();
      Quantity quantityToSell = new Quantity(2);

      cut.sell(quantityToSell);

      assertThat(cut.quantity()).isEqualTo(originalQuantity.decrease(quantityToSell));
      assertThat(cut.status()).isEqualTo(AdStatus.PUBLISHED);
    }

    @Test
    void noMoreStock() {
      Ad cut = AdFixture.WOODEN_CUP_AD_WITH_QUANTITY(2);
      Quantity quantityToSell = new Quantity(2);

      cut.sell(quantityToSell);

      assertTrue(cut.quantity().isNotAvailable());
      assertThat(cut.status()).isEqualTo(AdStatus.SOLD_OUT);
    }
  }
}
