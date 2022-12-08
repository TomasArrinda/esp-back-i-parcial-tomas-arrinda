package com.dh.movie.service;

import com.dh.movie.model.Movie;
import com.dh.movie.repository.MovieRepositoryMongo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepositoryMongo movieRepositoryMongo;

    public MovieService(MovieRepositoryMongo movieRepositoryMongo) {
        this.movieRepositoryMongo = movieRepositoryMongo;
    }
    public List<Movie> findByGenre(String genre) {
        return movieRepositoryMongo.findByGenre(genre);
    }
    public Movie save(Movie movie) {
        return movieRepositoryMongo.insert(movie);
    }
    public List<Movie> getAll() {
        return movieRepositoryMongo.findAll();
    }
}