package com.decathlon.techday.dddworkshop.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatRuntimeException;

import com.decathlon.techday.dddworkshop.fixtures.AdFixture;
import org.junit.jupiter.api.Nested;
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

}
