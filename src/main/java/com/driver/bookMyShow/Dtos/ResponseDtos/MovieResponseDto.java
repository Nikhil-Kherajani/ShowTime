package com.driver.bookMyShow.Dtos.ResponseDtos;

import com.driver.bookMyShow.Enums.Genre;
import com.driver.bookMyShow.Enums.Language;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MovieResponseDto {
    private int id;
    private String movieName;
    private Genre genre;
    private Language language;
    private double rating;
    private double duration;
    private LocalDate releaseDate;
    private String posterUrl; // New field
    private String trailerUrl; // New field
    private String description; // New field
}
