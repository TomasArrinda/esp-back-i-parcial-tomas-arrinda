package com.dh.movie.service;

import com.dh.movie.event.NewMovie;
import com.dh.movie.model.Movie;
import com.dh.movie.repository.MovieRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private final MovieRepositoryMongo movieRepositoryMongo;
    private final NewMovie newMovie;

    public MovieService(MovieRepositoryMongo movieRepositoryMongo, NewMovie newMovie) {
        this.movieRepositoryMongo = movieRepositoryMongo;
        this.newMovie = newMovie;
    }
    public List<Movie> findByGenre(String genre) {
        return movieRepositoryMongo.findByGenre(genre);
    }
    public Movie save(Movie movie) {
        newMovie.execute(movie);
        return movieRepositoryMongo.insert(movie);
    }
}