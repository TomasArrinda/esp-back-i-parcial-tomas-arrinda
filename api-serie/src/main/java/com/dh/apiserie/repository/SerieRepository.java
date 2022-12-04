package com.dh.apiserie.repository;

import com.dh.apiserie.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SerieRepository extends JpaRepository<Series, Long> {
    List<Series> findByGenre(String genre);
}