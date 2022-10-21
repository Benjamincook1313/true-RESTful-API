package com.rest.demo.restApi.payrollRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadData {

  private static final Logger logger = LoggerFactory.getLogger(LoadData.class);

  @Bean
  CommandLineRunner initDatabase(EmployeeRepo repo){
    return args -> {
      logger.info("Preloading " + repo.save(new Employee("Bilbo Baggins", "burglar")));
      logger.info("Preloading " + repo.save(new Employee("Frodo Baggins", "thief")));
    };
  }

}
