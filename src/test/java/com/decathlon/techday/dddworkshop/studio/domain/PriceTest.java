package com.decathlon.techday.dddworkshop.studio.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.decathlon.techday.dddworkshop.studio.domain.models.Price;
import java.util.Currency;
import java.util.stream.Stream;
import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Negative;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PriceTest {

  public static final Currency EUR = Currency.getInstance("EUR");

  @Property
  @Label("Discount amount must be positive")
  void discountMustBePositive(@ForAll @Negative int negativeDiscount) {
    Price cut = new Price(30, EUR);

    assertThatIllegalArgumentException()
      .isThrownBy(() -> cut.discount(negativeDiscount))
      .withMessage("Discount amount must be greater than 0");
  }

  @Property
  @Label("Discount amount must be lower than 100")
  void discountMustBeLowerThan100(@ForAll @IntRange(min = 101) int discount) {
    Price cut = new Price(30, EUR);

    assertThatIllegalArgumentException()
      .isThrownBy(() -> cut.discount(discount))
      .withMessage("Discount amount must be lower than 100");
  }

  @Nested
  class Constructor {

    @Test
    void negativeAmount() {
      assertThatIllegalArgumentException()
        .isThrownBy(() -> new Price(-10.00f, Currency.getInstance("EUR")))
        .withMessage("Amount must be positive");
    }

    @Test
    void positiveAmount() {
      assertThatNoException()
        .isThrownBy(() -> new Price(10.00f, Currency.getInstance("EUR")));
    }
  }

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

  }

}
