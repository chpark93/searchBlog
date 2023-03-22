package com.ch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ChRankApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChRankApplication.class, args);
    }
}
