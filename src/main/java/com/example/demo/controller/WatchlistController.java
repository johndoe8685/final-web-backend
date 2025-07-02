package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.data.dto.MovieSummaryDto;
import com.example.demo.service.WatchlistService;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/watchlist")
@RequiredArgsConstructor
public class WatchlistController {

    private final WatchlistService watchlistService;

    @GetMapping
    public ResponseEntity<List<MovieSummaryDto>> getUserWatchlist(@PathVariable Long userId) {
        List<MovieSummaryDto> watchlist = watchlistService.getWatchlistByUserId(userId);
        return ResponseEntity.ok(watchlist);
    }

    @PostMapping("/{movieId}")
    public ResponseEntity<Void> addToWatchlist(@PathVariable Long userId, @PathVariable Long movieId) {
        watchlistService.addMovieToWatchlist(userId, movieId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<Void> removeFromWatchlist(@PathVariable Long userId, @PathVariable Long movieId) {
        watchlistService.removeMovieFromWatchlist(userId, movieId);
        return ResponseEntity.noContent().build();
    }
}