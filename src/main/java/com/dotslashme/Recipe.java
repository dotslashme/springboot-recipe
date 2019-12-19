package com.dotslashme;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Recipe {

  public static void main(String[] args) {
    if (args.length > 0) {
      args = new String[0];
    }

    SpringApplication.run(Recipe.class, args);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
