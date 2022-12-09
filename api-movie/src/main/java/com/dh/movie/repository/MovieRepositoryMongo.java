package com.dh.movie.repository;

import com.dh.movie.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MovieRepositoryMongo extends MongoRepository<Movie,Long> {
    List<Movie> findByGenre(String genre);
    @Query
    List<Movie> findAll();
}