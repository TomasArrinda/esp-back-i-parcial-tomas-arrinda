package com.dh.apiserie.controller;

import com.dh.apiserie.event.SerieLogging;
import com.dh.apiserie.model.Series;
import com.dh.apiserie.repository.ChapterRepositoryMongo;
import com.dh.apiserie.repository.SeasonRepositoryMongo;
import com.dh.apiserie.repository.SerieRepositoryMongo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/serie")
public class SeriesController {
    private final SerieRepositoryMongo serieRepositoryMongo;
    private final SeasonRepositoryMongo seasonRepositoryMongo;
    private final ChapterRepositoryMongo chapterRepositoryMongo;
    private SerieLogging serieLogging;

    public SeriesController(SerieRepositoryMongo sr, SeasonRepositoryMongo ss, ChapterRepositoryMongo c) {
        this.serieRepositoryMongo = sr;
        this.seasonRepositoryMongo = ss;
        this.chapterRepositoryMongo = c;
    }
    @PostMapping
    public ResponseEntity<Series> addSerie(Series o) {
        String id = UUID.randomUUID().toString();
        serieLogging.sendMessage(new SerieLogging.SerieLoggingData(id));
        return ResponseEntity.ok().body(serieRepositoryMongo.insert(o));
    }
    @GetMapping("/{genre}")
    public ResponseEntity<List<Series>> getGenre(@PathVariable String genre) {
        return ResponseEntity.ok(serieRepositoryMongo.findByGenre(genre));
    }
}