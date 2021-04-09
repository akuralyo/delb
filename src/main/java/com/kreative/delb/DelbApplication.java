package com.kreative.delb;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DelbApplication {

  private static final Logger LOGGER = Logger.getLogger(DelbApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(DelbApplication.class, args);
    LOGGER.info("Welcome =)");
  }
}
