package com.decathlon.techday.dddworkshop;

import com.decathlon.techday.dddworkshop.shared.domain.MusicianId;
import com.decathlon.techday.dddworkshop.studio.domain.Instrument;
import com.decathlon.techday.dddworkshop.studio.domain.InstrumentRepository;
import java.util.Currency;
import java.util.UUID;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DddworkshopApplication {

  public static void main(String[] args) {
    SpringApplication.run(DddworkshopApplication.class, args);
  }

  @Bean
  public CommandLineRunner demo(InstrumentRepository repository) {
    return (args) -> {
      Instrument fenderTelecaster = new Instrument(UUID.randomUUID(),
        new MusicianId(UUID.fromString("cbfd5b36-8467-4060-b5a7-e4693de9e16e")),
        "Fender Telecaster American 2", 2099.90f, Currency.getInstance("EUR"));

      repository.save(fenderTelecaster);
    };
  }
}
