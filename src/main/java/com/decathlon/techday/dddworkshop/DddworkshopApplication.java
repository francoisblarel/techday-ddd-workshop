package com.decathlon.techday.dddworkshop;

import com.decathlon.techday.dddworkshop.musician.domain.MusicianRepository;
import com.decathlon.techday.dddworkshop.musician.domain.models.Musician;
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
  public CommandLineRunner demo(MusicianRepository musicianRepository) {
    return (args) -> {
      Musician bobMarley = new Musician("Bob Marley");
      Musician jimiHendrix = new Musician("Jimi Hendrix");
      jimiHendrix.gainReputation();
      
      musicianRepository.save(jimiHendrix);
      musicianRepository.save(bobMarley);
    };
  }
}
