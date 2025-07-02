package com.example.demo.data.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {
    private String user;
    
    private String text;
    private String country;
}