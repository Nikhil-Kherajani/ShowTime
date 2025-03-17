package com.driver.bookMyShow.Transformers;

import com.driver.bookMyShow.Dtos.RequestDtos.MovieEntryDto;
import com.driver.bookMyShow.Dtos.ResponseDtos.MovieResponseDto;
import com.driver.bookMyShow.Models.Movie;

public class MovieTransformer {

    public static MovieEntryDto convertToDto(Movie movie) {
        MovieEntryDto movieEntryDto = new MovieEntryDto();

        movieEntryDto.setMovieName(movie.getMovieName());
        movieEntryDto.setGenre(movie.getGenre());
        movieEntryDto.setLanguage(movie.getLanguage());
        movieEntryDto.setRating(movie.getRating());
        movieEntryDto.setDuration(movie.getDuration());
        movieEntryDto.setReleaseDate(movie.getReleaseDate());
        movieEntryDto.setPosterUrl(movie.getPosterUrl()); // Add new fields
        movieEntryDto.setTrailerUrl(movie.getTrailerUrl()); // Add new fields
        movieEntryDto.setDescription(movie.getDescription()); // Add new fields
        return movieEntryDto;
    }

    public static Movie movieDtoToMovie(MovieEntryDto movieEntryDto) {
        Movie movie = Movie.builder()
                .movieName(movieEntryDto.getMovieName())
                .duration(movieEntryDto.getDuration())
                .genre(movieEntryDto.getGenre())
                .language(movieEntryDto.getLanguage())
                .releaseDate(movieEntryDto.getReleaseDate())
                .rating(movieEntryDto.getRating())
                .posterUrl(movieEntryDto.getPosterUrl()) // Add new fields
                .trailerUrl(movieEntryDto.getTrailerUrl()) // Add new fields
                .description(movieEntryDto.getDescription()) // Add new fields
                .build();

        return movie;
    }
}
