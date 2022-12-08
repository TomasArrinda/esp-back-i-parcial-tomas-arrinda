package com.dh.apiserie.repository;

import com.dh.apiserie.model.Chapter;
import com.dh.apiserie.model.Series;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepositoryMongo extends MongoRepository<Chapter,Long> {
}