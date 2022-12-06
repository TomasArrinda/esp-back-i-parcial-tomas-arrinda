package com.dh.apiserie.controller;

import com.dh.apiserie.event.SerieLogging;
import com.dh.apiserie.model.Series;
import com.dh.apiserie.repository.ChapterRepository;
import com.dh.apiserie.repository.SeasonRepository;
import com.dh.apiserie.repository.SerieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/serie")
public class SeriesController {
    private final SerieRepository serieRepository;
    private final SeasonRepository seasonRepository;
    private final ChapterRepository chapterRepository;
    private SerieLogging serieLogging;

    public SeriesController(SerieRepository sr, SeasonRepository ss, ChapterRepository c) {
        this.serieRepository = sr;
        this.seasonRepository = ss;
        this.chapterRepository = c;
    }
    @PostMapping
    public ResponseEntity<Series> addSerie(Series o) {
        serieRepository.save(o);
        String id = UUID.randomUUID().toString();
        serieLogging.sendMessage(new SerieLogging.SerieLoggingData(id));
        return ResponseEntity.ok().body(serieRepository.save(o));
    }
    @GetMapping("/{genre}")
    public ResponseEntity<List<Series>> getGenre(@PathVariable String genre) {
        return ResponseEntity.ok(serieRepository.findByGenre(genre));
    }
}