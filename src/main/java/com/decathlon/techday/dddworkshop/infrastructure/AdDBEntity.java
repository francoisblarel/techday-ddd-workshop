package com.decathlon.techday.dddworkshop.infrastructure;

import com.decathlon.techday.dddworkshop.domain.Ad;
import com.decathlon.techday.dddworkshop.domain.AdStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class AdDBEntity {

  @Id
  private UUID id;
  private float price;
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
    dbObject.setQuantity(ad.quantity());
    // map fields from Ad to AdDBEntity
    return dbObject;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setPrice(float price) {
    this.price = price;
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
    Ad ad = new Ad(id, price, name, description, status, quantity);
    // map fields from AdDBEntity to Ad
    return ad;
  }
}
