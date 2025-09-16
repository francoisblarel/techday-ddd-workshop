package com.decathlon.techday.dddworkshop.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class QuantityTest {

  @Nested
  class IsZero {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 10, 42})
    void isNotZero(int value) {
      Quantity cut = new Quantity(value);

      assertFalse(cut.isZero());
    }

    @Test
    void isZero() {
      Quantity cut = new Quantity(0);

      assertTrue(cut.isZero());
    }
  }

  @Nested
  class Decrease {

    @Test
    void lowerThan() {
      Quantity cut = new Quantity(10);

      assertThatIllegalArgumentException()
        .isThrownBy(() -> cut.decrease(new Quantity(20)))
        .withMessage("Cannot decrease more than current value");
    }

    @Test
    void success() {
      Quantity cut = new Quantity(10);

      Quantity result = cut.decrease(new Quantity(3));

      assertThat(result).isEqualTo(new Quantity(7));
    }
  }

}
