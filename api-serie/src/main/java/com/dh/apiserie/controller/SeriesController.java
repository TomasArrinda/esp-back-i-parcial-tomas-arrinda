package com.dh.apiserie.controller;

import com.dh.apiserie.event.NewSerie;
import com.dh.apiserie.model.Series;
import com.dh.apiserie.repository.SerieRepositoryMongo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/serie")
public class SeriesController {
    private final SerieRepositoryMongo serieRepositoryMongo;
    private final NewSerie newSerie;

    public SeriesController(SerieRepositoryMongo sr, NewSerie ns) {
        this.serieRepositoryMongo = sr;
        this.newSerie = ns;
    }
    @PostMapping("/save")
    public ResponseEntity<Series> addSerie(Series o) {
        newSerie.execute(o);
        return ResponseEntity.ok().body(serieRepositoryMongo.insert(o));
    }
    @GetMapping("/{genre}")
    public ResponseEntity<List<Series>> getGenre(@PathVariable String genre) {
        return ResponseEntity.ok(serieRepositoryMongo.findByGenre(genre));
    }
}