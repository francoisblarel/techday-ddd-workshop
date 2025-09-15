package com.decathlon.techday.dddworkshop.infrastructure.configuration;

import com.decathlon.techday.dddworkshop.domain.AdRepository;
import com.decathlon.techday.dddworkshop.infrastructure.PostgresAdRepository;
import com.decathlon.techday.dddworkshop.infrastructure.SpringAdRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DDDWorkshopConfiguration {

  @Bean
  public AdRepository adRepository(SpringAdRepository springAdRepository) {
    return new PostgresAdRepository(springAdRepository);
  }

}
