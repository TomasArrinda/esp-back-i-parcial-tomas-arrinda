package com.dh.catalog.controller;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.model.Movie;
import com.dh.catalog.model.Series;
import com.dh.catalog.service.CatalogService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.catalog.Catalog;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/catalog")
public class CatalogController {
	private final CatalogService catalogService;

	public CatalogController(CatalogService catalogService) {
		this.catalogService = catalogService;
	}
	@GetMapping("/online/{genre}")
	@ResponseStatus(code = HttpStatus.OK)
	ResponseEntity<List> getByGenreOnline(@PathVariable String genre) {
		List list = catalogService.getMovieAndServiceByGenre(genre);
		return ResponseEntity.ok(list);
	}
	@GetMapping("/offline/{genre}")
	@ResponseStatus(code = HttpStatus.OK)
	ResponseEntity<List> getByGenreOffline(@PathVariable String genre) {
		List list = catalogService.getMovieAndServiceByGenreOffline(genre);
		return ResponseEntity.ok(list);
	}
	@PostMapping("/saveMovie")
	ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
		return ResponseEntity.ok().body(catalogService.saveMovie(movie));
	}
	@PostMapping("/saveSeries")
	ResponseEntity<Series> saveSeries(@RequestBody Series serie) {
		return ResponseEntity.ok().body(catalogService.saveSeries(serie));
	}
}