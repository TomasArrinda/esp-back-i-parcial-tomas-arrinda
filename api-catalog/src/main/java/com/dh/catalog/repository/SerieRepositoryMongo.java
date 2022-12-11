package com.dh.catalog.repository;

import com.dh.catalog.model.Series;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerieRepositoryMongo extends MongoRepository<Series,Long> {
    List<Series> findByGenre(String genre);
}