package com.driver.bookMyShow.Repositories;

import com.driver.bookMyShow.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Integer> {

    @Query(value = "select time from SHOWS where date = :date and movie_id = :movieId and theater_id = :theaterId", nativeQuery = true)
    public List<Time> getShowTimingsOnDate(@Param("date") Date date, @Param("theaterId") Integer theaterId,
            @Param("movieId") Integer movieId);

    @Query(value = "select movie_id from SHOWS group by movie_id order by count(*) desc limit 1", nativeQuery = true)
    public Integer getMostShowsMovie();

    @Query(value = "select * from SHOWS where movie_id = :movieId", nativeQuery = true)
    public List<Show> getAllShowsOfMovie(@Param("movieId") Integer movieId);

    List<Show> findByDateAndMovieIdAndTheaterId(LocalDate date, int movieId, int theaterId);

    List<Show> findByDateAndMovieId(LocalDate date, int movieId);

    List<Show> findByDateAndTheaterId(LocalDate date, int theaterId);

    List<Show> findByDate(LocalDate date);

}
