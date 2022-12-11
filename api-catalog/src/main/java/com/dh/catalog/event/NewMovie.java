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
    public void execute(NewMovie.Data data) {
        Movie movieNew = new Movie();
        BeanUtils.copyProperties(data.getMovie(),movieNew);
        movieRepositoryMongo.deleteById(data.getMovie().getId());
        movieRepositoryMongo.save(movieNew);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data implements Serializable {
        private MovieDto movie =new MovieDto();

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        class MovieDto{
            private Long id;
            private String name;
            private String genre;
            private String urlStream;
        }
    }
}