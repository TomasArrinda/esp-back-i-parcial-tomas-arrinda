package com.dh.catalog.service;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.event.NewMovie;
import com.dh.catalog.event.NewSerie;
import com.dh.catalog.model.Movie;
import com.dh.catalog.model.Series;
import com.dh.catalog.repository.SerieRepositoryMongo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
import com.dh.catalog.repository.MovieRepositoryMongo;
import java.util.List;

@Service
public class CatalogService {
    private final MovieRepositoryMongo movieRepositoryMongo;
    private final SerieRepositoryMongo serieRepositoryMongo;
    private final MovieServiceClient movieServiceClient;
    private final SerieServiceClient serieServiceClient;
    private final NewMovie newMovieEvent;
    private final NewSerie newSeriesEvent;

    public CatalogService(MovieRepositoryMongo movieRepositoryMongo, SerieRepositoryMongo serieRepositoryMongo, MovieServiceClient movieServiceClient, SerieServiceClient serieServiceClient, NewMovie newMovieEvent, NewSerie newSeriesEvent) {
        this.movieRepositoryMongo = movieRepositoryMongo;
        this.serieRepositoryMongo = serieRepositoryMongo;
        this.movieServiceClient = movieServiceClient;
        this.serieServiceClient = serieServiceClient;
        this.newMovieEvent = newMovieEvent;
        this.newSeriesEvent = newSeriesEvent;
    }

    public List getMovieAndServiceByGenre(String genre){
        List response = getMovieAndServiceByGenreOnline(genre);
        return response;
    }
    @Retry(name = "retryGenre")
    @CircuitBreaker(name = "clientGenre", fallbackMethod = "getMovieAndServiceByGenreOffline")
    public List getMovieAndServiceByGenreOnline(String genre) {
        List response = movieServiceClient.getMovieByGenre(genre);
        response.add(serieServiceClient.getSeriesByGenre(genre));
        return response;
    }
    public List getMovieAndServiceByGenreOffline(String genre) {
        List response = movieRepositoryMongo.findByGenre(genre);
        response.add(serieRepositoryMongo.findByGenre(genre));
        return response;
    }
    @Retry(name = "retrySaveMovie")
    @CircuitBreaker(name = "clientNewMovie", fallbackMethod = "saveMovieOffline")
    public Movie saveMovie(Movie movie) {
        movieServiceClient.save(movie);
        newMovieEvent.execute(movie);
        return movie;
    }
    public Movie saveMovieOffline(Movie movie) {
        movieRepositoryMongo.save(movie);
        newMovieEvent.execute(movie);
        return movie;
    }
    @Retry(name = "retrySaveSeries")
    @CircuitBreaker(name = "clientNewSeries", fallbackMethod = "saveSeriesOffline")
    public Series saveSeries(Series serie) {
        serieServiceClient.save(serie);
        newSeriesEvent.execute(serie);
        return serie;
    }
    public Series saveSeriesOffline(Series serie) {
        serieRepositoryMongo.save(serie);
        newSeriesEvent.execute(serie);
        return serie;
    }
}