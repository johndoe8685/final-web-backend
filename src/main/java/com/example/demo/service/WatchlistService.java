package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.data.dto.MovieSummaryDto;
import com.example.demo.data.entity.Movie;
import com.example.demo.data.entity.Watchlist;
import com.example.demo.data.repository.MovieRepository;
import com.example.demo.data.repository.UserRepository;
import com.example.demo.data.repository.WatchlistRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WatchlistService {

    private final WatchlistRepository watchlistRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public List<MovieSummaryDto> getWatchlistByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with id: " + userId);
        }

        List<Watchlist> watchlistEntries = watchlistRepository.findByUserId(userId);

        return watchlistEntries.stream()
                .map(Watchlist::getMovie)
                .map(this::convertToMovieSummaryDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void addMovieToWatchlist(Long userId, Long movieId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with id: " + userId);
        }
        if (!movieRepository.existsById(movieId)) {
            throw new RuntimeException("Movie not found with id: " + movieId);
        }

        if (watchlistRepository.existsByUserIdAndMovieId(userId, movieId)) {
            throw new RuntimeException("Movie with id " + movieId + " is already in the watchlist for user " + userId);
        }

        Watchlist newEntry = new Watchlist();
        newEntry.setUserId(userId);
        newEntry.setMovieId(movieId);

        watchlistRepository.save(newEntry);
    }

    @Transactional
    public void removeMovieFromWatchlist(Long userId, Long movieId) {
        if (!watchlistRepository.existsByUserIdAndMovieId(userId, movieId)) {
            throw new RuntimeException("Movie with id " + movieId + " not found in watchlist for user " + userId);
        }

        watchlistRepository.deleteByUserIdAndMovieId(userId, movieId);
    }

    private MovieSummaryDto convertToMovieSummaryDto(Movie movie) {
        return new MovieSummaryDto(
                movie.getId(),
                movie.getTitle(),
                movie.getPosterUrl(),
                movie.getRating()
        );
    }
}
