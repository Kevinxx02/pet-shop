package com.petshop.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableScheduling
@SpringBootApplication
@EntityScan("com.petshop.catalog.infrastructure.persistence")
public class PetStoreCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetStoreCatalogApplication.class, args);
    }
}
