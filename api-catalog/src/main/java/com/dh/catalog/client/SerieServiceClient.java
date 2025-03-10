package com.dh.catalog.client;

import com.dh.catalog.model.Series;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.annotation.Repeatable;
import java.util.List;

@FeignClient(name="api-serie")
public interface SerieServiceClient {
    @GetMapping("/serie/{genre}")
    List<SerieDto> getSeriesByGenre(@PathVariable (value = "genre") String genre);
    @PostMapping("/serie/save")
    ResponseEntity<Series> save(@RequestBody Series series);

    @Getter
    @Setter
    class SerieDto{
        private String id;
        private String name;
        private String genre;
        private List<SeasonDto> seasons;
    }
    @Getter
    @Setter
    class SeasonDto {
        private String id;
        private int seasonNumber;
        private List<ChapterDto> chapters;
    }
    @Getter
    @Setter
    class ChapterDto {
        private String id;
        private String name;
        private int number;
        private String urlStream;
    }
}