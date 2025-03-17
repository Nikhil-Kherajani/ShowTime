package com.driver.bookMyShow.Dtos.RequestDtos;

import com.driver.bookMyShow.Enums.Genre;
import com.driver.bookMyShow.Enums.Language;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MovieEntryDto {
    private String movieName;
    private Integer duration;
    private Double rating;
    private LocalDate releaseDate;
    private Genre genre;
    private Language language;
    private String posterUrl; // New field
    private String trailerUrl; // New field
    private String description; // New field
}
