package com.dh.catalog.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name="api-serie")
public interface SerieServiceClient {
    @GetMapping("/series/{genre}")
    List<SerieDto> getSeriesByGenre(@PathVariable (value = "genre") String genre);

    @Getter
    @Setter
    class SerieDto{
        private Long id;
        private String name;
        private String genre;
        private List<SeasonDto> seasons;
    }
    @Getter
    @Setter
    class SeasonDto {
        private Long id;
        private int seasonNumber;
        private List<ChapterDto> chapters;
    }
    @Getter
    @Setter
    class ChapterDto {
        private Long id;
        private String name;
        private int number;
        private String urlStream;
    }
}