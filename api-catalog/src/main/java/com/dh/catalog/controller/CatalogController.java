package com.dh.catalog.controller;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.event.Logging;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/catalog")
public class CatalogController {
	private final MovieServiceClient movieServiceClient;
	private final SerieServiceClient serieServiceClient;
	private Logging logging;

	public CatalogController(MovieServiceClient movieServiceClient, SerieServiceClient serieServiceClient) {
		this.movieServiceClient = movieServiceClient;
		this.serieServiceClient = serieServiceClient;
	}
	@GetMapping("/{genre}")
	ResponseEntity<List> getGenre(@PathVariable String genre) {
		String id = UUID.randomUUID().toString();
		List list = movieServiceClient.getMovieByGenre(genre);
		list.add(serieServiceClient.getSeriesByGenre(genre));
		logging.sendMessage(new Logging.LoggingData(id));
		return ResponseEntity.ok(list);
	}
}