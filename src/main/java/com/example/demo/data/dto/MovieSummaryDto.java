package com.example.demo.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieSummaryDto {
    private Long id;
    private String title;
    private String posterUrl;
    private double rating;
}