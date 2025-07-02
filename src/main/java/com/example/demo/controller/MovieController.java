package com.example.demo.controller;

import com.example.demo.data.dto.MovieDto;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieDto>> getMovies(@RequestParam(required = false) String search) {
        List<MovieDto> movies;
        if (search != null && !search.isEmpty()) {
            movies = movieService.searchMovies(search);
        } else {
            movies = movieService.getAllMovies();
        }
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id)
                .map(movieDto -> ResponseEntity.ok(movieDto))
                .orElse(ResponseEntity.notFound().build());
    }
}