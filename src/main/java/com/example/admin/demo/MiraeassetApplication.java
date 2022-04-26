package com.example.admin.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MiraeassetApplication {

  public static void main(String[] args) {
    SpringApplication.run(MiraeassetApplication.class, args);
  }

}
