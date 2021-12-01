package com.team.est;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient //启动eureka客户端
@MapperScan("com.team.est.mapper")
public class EstProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstProviderApplication.class, args);
    }

}
