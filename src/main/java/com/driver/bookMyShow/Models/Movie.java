package com.driver.bookMyShow.Models;

import com.driver.bookMyShow.Enums.Genre;
import com.driver.bookMyShow.Enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "MOVIES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String movieName;

    private String posterUrl; // New field
    private String trailerUrl; // New field

    @Column(columnDefinition = "TEXT") // Use TEXT for potentially long descriptions
    private String description; // New field

    private Integer duration;

    @Column(scale = 2)
    private Double rating;

    private LocalDate releaseDate;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @Enumerated(value = EnumType.STRING)
    private Language language;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Show> showList = new ArrayList<>();
}
