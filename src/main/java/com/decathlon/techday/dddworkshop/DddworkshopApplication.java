package com.decathlon.techday.dddworkshop;

import com.decathlon.techday.dddworkshop.domain.Ad;
import com.decathlon.techday.dddworkshop.domain.AdRepository;
import com.decathlon.techday.dddworkshop.domain.AdStatus;
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

  @Bean
  public CommandLineRunner demo(AdRepository repository) {
    return (args) -> {
      // save a few customers
      UUID id = UUID.randomUUID();
      repository.save(new Ad(id, 12.99f, "Wooden cup", "Handcrafted wooden cup with DDD initiales", AdStatus.DRAFT, 10));

      // fetch all customers
      log.info("Ad found with get(id):");
      log.info("-------------------------------");
      repository.get(id).ifPresent(ad ->
        log.info(ad.toString())
      );
      log.info("");
    };
  }
}
