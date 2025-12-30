package com.example.coachserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients

public class CoachServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoachServerApplication.class, args);
    }

}
