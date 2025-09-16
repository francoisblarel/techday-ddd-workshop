package com.decathlon.techday.dddworkshop.infrastructure;

import com.decathlon.techday.dddworkshop.domain.Ad;
import com.decathlon.techday.dddworkshop.domain.AdStatus;
import com.decathlon.techday.dddworkshop.domain.Price;
import com.decathlon.techday.dddworkshop.domain.Quantity;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class AdDBEntity {

  @Id
  private UUID id;
  @Embedded
  private PriceDBEntity price;
  private String name;
  private String description;
  @Enumerated(EnumType.STRING)
  private AdStatus status;
  private int quantity;

  public static AdDBEntity fromAd(Ad ad) {
    AdDBEntity dbObject = new AdDBEntity();
    dbObject.setId(ad.id());
    dbObject.setPrice(ad.price());
    dbObject.setName(ad.name());
    dbObject.setDescription(ad.description());
    dbObject.setStatus(ad.status());
    dbObject.setQuantity(ad.quantity().value());

    return dbObject;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setPrice(Price price) {
    this.price = PriceDBEntity.fromPrice(price);
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setStatus(AdStatus status) {
    this.status = status;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Ad toAd() {
    return new Ad(id, price.toPrice(), name, description, status, new Quantity(quantity));
  }
}
