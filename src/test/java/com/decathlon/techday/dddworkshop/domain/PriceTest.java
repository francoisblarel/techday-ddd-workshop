package com.decathlon.techday.dddworkshop.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Currency;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PriceTest {

  public static final Currency EUR = Currency.getInstance("EUR");

  @Nested
  class Discount {

    public static Stream<Arguments> discount() {
      return Stream.of(
        arguments(30f, 50f, 15f),
        arguments(200f, 33.3f, 133.4f)
      );
    }

    @ParameterizedTest
    @MethodSource("discount")
    void success(float amount, float discount, float expected) {
      Price cut = new Price(amount, EUR);

      Price discountedPrice = cut.discount(discount);
      assertThat(discountedPrice.amount()).isEqualTo(expected);
      assertThat(discountedPrice.currency()).isEqualTo(EUR);
    }

    // @Proposal: property-test
    @Test
    void lower_than_0() {
      Price cut = new Price(30, EUR);

      assertThatIllegalArgumentException()
        .isThrownBy(() -> cut.discount(-10))
        .withMessage("Discount amount must be greater than 0");
    }

    // @Proposal: property-test
    @Test
    void greater_than_100() {
      Price cut = new Price(30, EUR);

      assertThatIllegalArgumentException()
        .isThrownBy(() -> cut.discount(104))
        .withMessage("Discount amount must be lower than 100");
    }
  }
}
