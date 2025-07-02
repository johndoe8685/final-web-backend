package com.example.demo.data.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.data.entity.Watchlist;
import com.example.demo.data.entity.WatchlistId;

import java.util.List;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, WatchlistId> {
    List<Watchlist> findByUserId(Long userId);
    boolean existsByUserIdAndMovieId(Long userId, Long movieId);
    void deleteByUserIdAndMovieId(Long userId, Long movieId);
}