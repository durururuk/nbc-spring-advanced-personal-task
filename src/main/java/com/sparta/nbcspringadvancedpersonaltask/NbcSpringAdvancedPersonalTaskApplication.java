package com.sparta.nbcspringadvancedpersonaltask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NbcSpringAdvancedPersonalTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(NbcSpringAdvancedPersonalTaskApplication.class, args);
    }

}
