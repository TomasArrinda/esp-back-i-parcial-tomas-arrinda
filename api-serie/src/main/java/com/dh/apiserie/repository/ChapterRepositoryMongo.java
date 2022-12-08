package com.dh.apiserie.repository;

import com.dh.apiserie.model.Chapter;
import com.dh.apiserie.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChapterRepositoryMongo extends MongoRepository<Chapter,Long> {
}