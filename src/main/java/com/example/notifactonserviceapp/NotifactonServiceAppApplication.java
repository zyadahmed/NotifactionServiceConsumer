package com.example.notifactonserviceapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NotifactonServiceAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotifactonServiceAppApplication.class, args);
    }

}
