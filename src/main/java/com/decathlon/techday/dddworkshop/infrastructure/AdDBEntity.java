package com.decathlon.techday.dddworkshop.infrastructure;

import com.decathlon.techday.dddworkshop.domain.Ad;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class AdDBEntity {

  @Id
  private UUID id;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Ad toAd() {
    Ad ad = new Ad(id);
    // map fields from AdDBEntity to Ad
    return ad;
  }

  public static AdDBEntity fromAd(Ad ad) {
    AdDBEntity dbObject = new AdDBEntity();
    dbObject.setId(ad.id());
    // map fields from Ad to AdDBEntity
    return dbObject;
  }
}
