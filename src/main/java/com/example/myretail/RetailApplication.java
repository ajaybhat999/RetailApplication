package com.example.myretail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by akrish10 on 5/26/20.
 */
@SpringBootApplication
@Slf4j
public class RetailApplication {

    public static void main(String[] args) {
        SpringApplication.run(RetailApplication.class, args);
        log.info("My retail Application started!");
    }
}
