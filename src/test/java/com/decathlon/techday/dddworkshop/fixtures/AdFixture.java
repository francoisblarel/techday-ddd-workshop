package com.decathlon.techday.dddworkshop.fixtures;

import com.decathlon.techday.dddworkshop.domain.Ad;
import com.decathlon.techday.dddworkshop.domain.AdStatus;
import com.decathlon.techday.dddworkshop.domain.Price;
import com.decathlon.techday.dddworkshop.domain.Quantity;
import java.util.Currency;
import java.util.UUID;

public class AdFixture {

  public static Ad WOODEN_CUP_AD() {
    return new Ad(UUID.fromString("f3a66477-591c-4ca8-8549-0909648f23a5"),
      new Price(13.00f, Currency.getInstance("EUR")), "Wooden cup",
      "Handcrafted wooden cup with DDD initiales", AdStatus.DRAFT, new Quantity(10));
  }

  public static Ad WOODEN_CUP_AD_WITH_QUANTITY(int quantity) {
    return new Ad(UUID.fromString("f3a66477-591c-4ca8-8549-0909648f23a5"),
      new Price(13.00f, Currency.getInstance("EUR")), "Wooden cup",
      "Handcrafted wooden cup with DDD initiales", AdStatus.DRAFT, new Quantity(quantity));
  }
}
