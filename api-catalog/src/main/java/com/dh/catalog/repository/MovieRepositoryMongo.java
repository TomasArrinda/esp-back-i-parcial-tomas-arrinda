package com.dh.catalog.repository;

import com.dh.catalog.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepositoryMongo extends MongoRepository<Movie,Long> {
    @Query
    List<Movie> findByGenre(String genre);
}