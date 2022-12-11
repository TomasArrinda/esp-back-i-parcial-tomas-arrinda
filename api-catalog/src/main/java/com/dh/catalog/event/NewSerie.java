package com.dh.catalog.event;

import com.dh.catalog.config.RabbitMQConfig;
import com.dh.catalog.model.Movie;
import com.dh.catalog.model.Series;
import com.dh.catalog.repository.MovieRepositoryMongo;
import com.dh.catalog.repository.SerieRepositoryMongo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Component
public class NewSerie {
    private final SerieRepositoryMongo serieRepositoryMongo;

    public NewSerie(SerieRepositoryMongo serieRepositoryMongo) {
        this.serieRepositoryMongo = serieRepositoryMongo;
    }
    @RabbitListener(queues = RabbitMQConfig.TOPIC_NEW_SERIES)
    public void execute(NewSerie.Data data) {
        Series seriesNew = new Series();
        BeanUtils.copyProperties(data.getSerie(),seriesNew);
        if (data.getSerie().getSeriesId() != null && seriesNew.getSeasons() != null) {
            BeanUtils.copyProperties(data.getSerie().getSeasons(),seriesNew.getSeasons());
        }
        serieRepositoryMongo.deleteById(data.getSerie().getSeriesId());
        serieRepositoryMongo.save(seriesNew);
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
            private List<Data.SerieDto.SeasonDto> seasons;

            @AllArgsConstructor
            @NoArgsConstructor
            @Getter
            @Setter
            public class SeasonDto {
                @Serial
                private static final long serialVersionUID = 1L;
                private Long seasonId;
                private int seasonNumber;
                private List<Data.SerieDto.SeasonDto.ChapterDto> chapters;

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