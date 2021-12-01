package com.team.est;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EstServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstServerApplication.class, args);
    }

}
