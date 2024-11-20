package com.example.modernsoftware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ModernsoftwareApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModernsoftwareApplication.class, args);
    }
}
