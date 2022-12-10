package com.dh.apiserie.controller;

import com.dh.apiserie.model.Series;
import com.dh.apiserie.repository.SerieRepositoryMongo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/serie")
public class SeriesController {
    private final SerieRepositoryMongo serieRepositoryMongo;

    public SeriesController(SerieRepositoryMongo sr) {
        this.serieRepositoryMongo = sr;
    }
    @PostMapping
    public ResponseEntity<Series> addSerie(Series o) {
        return ResponseEntity.ok().body(serieRepositoryMongo.insert(o));
    }
    @GetMapping("/{genre}")
    public ResponseEntity<List<Series>> getGenre(@PathVariable String genre) {
        return ResponseEntity.ok(serieRepositoryMongo.findByGenre(genre));
    }
}