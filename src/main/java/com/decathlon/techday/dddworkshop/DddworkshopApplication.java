package com.decathlon.techday.dddworkshop;

import com.decathlon.techday.dddworkshop.domain.Ad;
import com.decathlon.techday.dddworkshop.domain.AdRepository;
import com.decathlon.techday.dddworkshop.domain.AdStatus;
import com.decathlon.techday.dddworkshop.domain.Price;
import com.decathlon.techday.dddworkshop.domain.Quantity;
import java.util.Currency;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DddworkshopApplication {

  private static final Logger log = LoggerFactory.getLogger(DddworkshopApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(DddworkshopApplication.class, args);
  }

  private static void displayAd(AdRepository repository, UUID id) {
    log.info("Ad found with get(id):");
    log.info("-------------------------------");
    repository.get(id).ifPresent(ad ->
      log.info(ad.toString())
    );
    log.info("");
  }

  @Bean
  public CommandLineRunner demo(AdRepository repository) {
    return (args) -> {
      UUID id = UUID.randomUUID();
      Ad cupAd = new Ad(id, new Price(12.99f, Currency.getInstance("EUR")), "Wooden cup",
        "Handcrafted wooden cup with DDD initiales", AdStatus.DRAFT, new Quantity(10));

      repository.save(cupAd);
      displayAd(repository, id);

      // apply discount
      cupAd.applyDiscount(10);
      repository.save(cupAd);
      displayAd(repository, id);
    };
  }
}
