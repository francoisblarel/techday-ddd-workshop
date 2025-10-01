package com.decathlon.techday.dddworkshop.musician.infrastructure.rest.dtos;

import com.decathlon.techday.dddworkshop.musician.application.queries.views.MusicianProfile;
import com.decathlon.techday.dddworkshop.musician.application.queries.views.MusicianProfile.InstrumentView;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MusicianProfileDto {

  private String name;
  private String reputation;
  private List<InstrumentDto> sells;
  private List<InstrumentDto> buyingHistory;

  public MusicianProfileDto() {
  }

  public MusicianProfileDto(String name, String reputation, List<InstrumentDto> sells,
    List<InstrumentDto> buyingHistory) {
    this.name = name;
    this.reputation = reputation;
    this.sells = sells;
    this.buyingHistory = buyingHistory;
  }

  public MusicianProfileDto(MusicianProfile profile) {
    this.name = profile.name();
    this.reputation = profile.reputation().toString();
    this.sells = profile.sells().stream().map(InstrumentDto::new).collect(Collectors.toList());
    this.buyingHistory = profile.buyingHistory().stream().map(InstrumentDto::new).collect(Collectors.toList());

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getReputation() {
    return reputation;
  }

  public void setReputation(String reputation) {
    this.reputation = reputation;
  }

  public List<InstrumentDto> getSells() {
    return sells;
  }

  public void setSells(List<InstrumentDto> sells) {
    this.sells = sells;
  }

  public List<InstrumentDto> getBuyingHistory() {
    return buyingHistory;
  }

  public void setBuyingHistory(List<InstrumentDto> buyingHistory) {
    this.buyingHistory = buyingHistory;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MusicianProfileDto that = (MusicianProfileDto) o;
    return Objects.equals(name, that.name) && Objects.equals(reputation, that.reputation)
      && Objects.equals(sells, that.sells) && Objects.equals(buyingHistory, that.buyingHistory);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, reputation, sells, buyingHistory);
  }

  public static class InstrumentDto {

    private String name;
    private String description;
    private String price;

    public InstrumentDto() {
    }

    public InstrumentDto(String name, String description, String price) {
      this.name = name;
      this.description = description;
      this.price = price;
    }

    public InstrumentDto(InstrumentView instrumentView) {
      this.name = instrumentView.name();
      this.description = instrumentView.description();
      this.price = instrumentView.price().toString();
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public String getPrice() {
      return price;
    }

    public void setPrice(String price) {
      this.price = price;
    }

    @Override
    public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      InstrumentDto that = (InstrumentDto) o;
      return Objects.equals(name, that.name) && Objects.equals(description, that.description)
        && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
      return Objects.hash(name, description, price);
    }
  }
}
