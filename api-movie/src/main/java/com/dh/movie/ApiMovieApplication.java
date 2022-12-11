package com.dh.movie;

import com.dh.movie.model.Movie;
import com.dh.movie.repository.MovieRepositoryMongo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableMongoRepositories
public class ApiMovieApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiMovieApplication.class, args);
    }
    @Bean
    public CommandLineRunner loadData(MovieRepositoryMongo repository) {
        return (args) -> {
            if (!repository.findAll().isEmpty()) {
                return;
            }
            repository.save(new Movie(null, "Pelicula 1", "Terror", "www.netflix.com"));
            repository.save(new Movie(null, "Pelicula 2", "Terror", "www.netflix.com"));
            repository.save(new Movie(null, "Pelicula 3", "Comedia", "www.netflix.com"));
            repository.save(new Movie(null, "Pelicula 4", "Ficcion", "www.netflix.com"));
        };
    }
}