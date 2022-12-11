package com.dh.catalog.event;

import com.dh.catalog.config.RabbitMQConfig;
import com.dh.catalog.model.Movie;
import com.dh.catalog.repository.MovieRepositoryMongo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

@Component
public class NewMovie {
    private final MovieRepositoryMongo movieRepositoryMongo;

    public NewMovie(MovieRepositoryMongo movieRepositoryMongo) {
        this.movieRepositoryMongo = movieRepositoryMongo;
    }
    @RabbitListener(queues = RabbitMQConfig.TOPIC_NEW_MOVIE)
    public void execute(Movie data) {
        Movie movieNew = new Movie();
        BeanUtils.copyProperties(data,movieNew);
        movieRepositoryMongo.deleteById(Long.valueOf(data.getId()));
        movieRepositoryMongo.save(movieNew);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data implements Serializable {
        private Movie movie = new Movie();

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        class Movie{
            private Long id;
            private String name;
            private String genre;
            private String urlStream;
        }
    }
}