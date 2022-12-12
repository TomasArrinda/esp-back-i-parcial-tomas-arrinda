package com.dh.apiserie.event;

import com.dh.apiserie.config.RabbitMQConfig;
import com.dh.apiserie.model.Series;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Component
public class NewSerie {
    private final RabbitTemplate rabbitTemplate;

    public NewSerie(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    @RabbitListener(queues = RabbitMQConfig.TOPIC_NEW_SERIES)
    public void execute(Series data) {
        Series seriesNew = new Series();
        BeanUtils.copyProperties(data,seriesNew);
        if (data.getId() != null && seriesNew.getSeasons() != null) {
            BeanUtils.copyProperties(data.getSeasons(),seriesNew.getSeasons());
        }
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.TOPIC_NEW_SERIES, data);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;
        private SerieDto serie =new SerieDto();

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        class SerieDto{
            @Serial
            private static final long serialVersionUID = 1L;
            private Long seriesId;
            private String name;
            private String genre;
            private List<SeasonDto> seasons;

            @AllArgsConstructor
            @NoArgsConstructor
            @Getter
            @Setter
            public class SeasonDto {
                @Serial
                private static final long serialVersionUID = 1L;
                private Long seasonId;
                private int seasonNumber;
                private List<ChapterDto> chapters;

                @AllArgsConstructor
                @NoArgsConstructor
                @Getter
                @Setter
                public class ChapterDto {
                    @Serial
                    private static final long serialVersionUID = 1L;
                    private Long chapterId;
                    private String name;
                    private int number;
                    private String urlStream;
                }
            }
        }
    }
}