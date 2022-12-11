package com.dh.catalog;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(clients = {MovieServiceClient.class, SerieServiceClient.class})
public class ApiCatalogApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiCatalogApplication.class, args);
    }
}