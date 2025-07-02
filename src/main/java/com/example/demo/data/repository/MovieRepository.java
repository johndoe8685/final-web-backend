package com.example.demo.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.data.entity.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT DISTINCT m FROM Movie m JOIN m.actors a WHERE LOWER(m.title) LIKE LOWER(CONCAT('%', :term, '%')) OR LOWER(a) LIKE LOWER(CONCAT('%', :term, '%'))")
    List<Movie> searchByTitleOrActor(@Param("term") String term);
    
}
