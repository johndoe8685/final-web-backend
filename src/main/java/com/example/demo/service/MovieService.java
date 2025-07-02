package com.example.demo.service;

import com.example.demo.data.dto.CommentDto;
import com.example.demo.data.dto.MovieDto;
import com.example.demo.data.entity.Comment;
import com.example.demo.data.entity.Movie;
import com.example.demo.data.repository.MovieRepository;
import jakarta.transaction.Transactional; // Önemli: LAZY fetching için
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(this::convertToMovieDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<MovieDto> getMovieById(Long id) {
        return movieRepository.findById(id)
                .map(this::convertToMovieDtoWithComments);
    }

    public List<MovieDto> searchMovies(String term) {
        if (term == null || term.trim().isEmpty()) {
            return List.of();
        }
        return movieRepository.searchByTitleOrActor(term).stream()
                .map(this::convertToMovieDto)
                .collect(Collectors.toList());
    }

    private MovieDto convertToMovieDto(Movie movie) {
        MovieDto dto = new MovieDto();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setDescription(movie.getDescription());
        dto.setPosterUrl(movie.getPosterUrl());
        dto.setTrailerUrl(movie.getTrailerUrl());
        dto.setRating(movie.getRating());
        dto.setActors(movie.getActors());
        return dto;
    }

    private MovieDto convertToMovieDtoWithComments(Movie movie) {
        MovieDto dto = convertToMovieDto(movie);

        List<CommentDto> commentDtos = movie.getComments().stream()
                .map(this::convertToCommentDto)
                .collect(Collectors.toList());
        dto.setComments(commentDtos);
        
        return dto;
    }

    private CommentDto convertToCommentDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setUser(comment.getUser());
        dto.setText(comment.getText());
        dto.setCountry(comment.getCountry());
        return dto;
    }
}