package com.dh.movie.event;

import com.dh.movie.config.RabbitMQConfig;
import com.dh.movie.model.Movie;
import com.dh.movie.repository.MovieRepositoryMongo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import java.io.Serializable;

@Component
public class NewMovie {
    private final RabbitTemplate rabbitTemplate;

    public NewMovie(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = RabbitMQConfig.TOPIC_NEW_MOVIE)
    public void execute(Movie data) {
        Movie movieNew = new Movie();
        BeanUtils.copyProperties(data,movieNew);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.TOPIC_NEW_MOVIE, data);
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