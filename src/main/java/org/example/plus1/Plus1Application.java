package org.example.plus1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Plus1Application {

  public static void main(String[] args) {
    SpringApplication.run(Plus1Application.class, args);
  }

}
