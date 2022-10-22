package com.rest.demo.restApi.payroll;

import com.rest.demo.restApi.payroll.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadData {

  // allows us to log data to the console while interacting with repository methods to
  private static final Logger log = LoggerFactory.getLogger(LoadData.class);

  @Bean
  CommandLineRunner initData(com.rest.demo.restApi.payroll.EmployeeRepo repo){ // runs when application context is loaded
    // requests a copy of EmployeeRepo
    // creates each entity below and stores them in the H2 DB
    return args -> {
      log.info("Preloading " + repo.save(new Employee("Bilbo", "Baggins", "burglar")));
      log.info("Preloading " + repo.save(new Employee("Frodo", "Baggins", "thief")));
      log.info("Preloading " + repo.save(new Employee("Samwise", "gamgee", "gardener")));
      log.info("Preloading " + repo.save(new Employee("Gandalf", "The Grey", "Wizard")));
      log.info("Preloading " + repo.save(new Employee("Gimli", "Durin", "Warrior")));
      log.info("Preloading " + repo.save(new Employee("Legolas", "Greenleaf", "Elf")));
    };
  }

}
