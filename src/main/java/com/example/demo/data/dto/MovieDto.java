package com.example.demo.data.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class MovieDto {

    private Long id;
    private String title;
    private String description;

    private List<String> actors;
    
    private String posterUrl;
    private String trailerUrl;
    private double rating;

    private List<CommentDto> comments;
}