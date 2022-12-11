package com.dh.apiserie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableMongoRepositories(basePackages = "com.dh.apiserie.repository")
public class ApiSerieApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiSerieApplication.class, args);
	}
}