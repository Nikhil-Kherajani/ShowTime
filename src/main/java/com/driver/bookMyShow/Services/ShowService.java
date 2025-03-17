package com.driver.bookMyShow.Services;

import com.driver.bookMyShow.Dtos.RequestDtos.AvailableShowsRequestDto;
import com.driver.bookMyShow.Dtos.RequestDtos.ShowEntryDto;
import com.driver.bookMyShow.Dtos.RequestDtos.ShowSeatEntryDto;
import com.driver.bookMyShow.Dtos.RequestDtos.ShowTimingsDto;
import com.driver.bookMyShow.Dtos.ResponseDtos.AvailableSeatsResponseDto;
import com.driver.bookMyShow.Dtos.ResponseDtos.ShowResponseDto;
import com.driver.bookMyShow.Enums.SeatType;
import com.driver.bookMyShow.Exceptions.MovieDoesNotExists;
import com.driver.bookMyShow.Exceptions.ShowDoesNotExists;
import com.driver.bookMyShow.Exceptions.TheaterDoesNotExists;
import com.driver.bookMyShow.Models.*;
import com.driver.bookMyShow.Repositories.MovieRepository;
import com.driver.bookMyShow.Repositories.ShowRepository;
import com.driver.bookMyShow.Repositories.TheaterRepository;
import com.driver.bookMyShow.Transformers.ShowTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    public String addShow(ShowEntryDto showEntryDto) throws MovieDoesNotExists, TheaterDoesNotExists {
        Show show = ShowTransformer.showDtoToShow(showEntryDto);

        Optional<Movie> movieOpt = movieRepository.findById(showEntryDto.getMovieId());
        if (movieOpt.isEmpty()) {
            throw new MovieDoesNotExists();
        }
        Optional<Theater> theaterOpt = theaterRepository.findById(showEntryDto.getTheaterId());
        if (theaterOpt.isEmpty()) {
            throw new TheaterDoesNotExists();
        }

        Theater theater = theaterOpt.get();
        Movie movie = movieOpt.get();

        show.setMovie(movie);
        show.setTheater(theater);
        show = showRepository.save(show);

        movie.getShowList().add(show);
        theater.getShowList().add(show);

        movieRepository.save(movie);
        theaterRepository.save(theater);

        return "Show has been added Successfully";
    }

    public String associateShowSeats(ShowSeatEntryDto showSeatEntryDto) throws ShowDoesNotExists {
        Optional<Show> showOpt = showRepository.findById(showSeatEntryDto.getShowId());
        if (showOpt.isEmpty()) {
            throw new ShowDoesNotExists();
        }
        Show show = showOpt.get();
        Theater theater = show.getTheater();

        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();

        List<ShowSeat> showSeatList = show.getShowSeatList();
        for (TheaterSeat theaterSeat : theaterSeatList) {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeatNo(theaterSeat.getSeatNo());
            showSeat.setSeatType(theaterSeat.getSeatType());

            if (showSeat.getSeatType().equals(SeatType.CLASSIC)) {
                showSeat.setPrice((showSeatEntryDto.getPriceOfClassicSeat()));
            } else {
                showSeat.setPrice(showSeatEntryDto.getPriceOfPremiumSeat());
            }

            showSeat.setShow(show);
            showSeat.setIsAvailable(Boolean.TRUE);
            showSeat.setIsFoodContains(Boolean.FALSE);

            showSeatList.add(showSeat);
        }
        showRepository.save(show);

        return "Show seats have been associated successfully";
    }

    public List<LocalTime> showTimingsOnDate(ShowTimingsDto showTimingsDto)
            throws TheaterDoesNotExists, MovieDoesNotExists {
        Optional<Theater> theaterOpt = theaterRepository.findById(showTimingsDto.getTheaterId());
        if (theaterOpt.isEmpty()) {
            throw new TheaterDoesNotExists();
        }

        Optional<Movie> movieOpt = movieRepository.findById(showTimingsDto.getMovieId());
        if (movieOpt.isEmpty()) {
            throw new MovieDoesNotExists();
        }

        Movie movie = movieOpt.get();
        Theater theater = theaterOpt.get();
        List<Show> showList = theater.getShowList();
        List<LocalTime> showTimings = new ArrayList<>();

        for (Show show : showList) {
            if (show.getMovie().equals(movie) && show.getDate().equals(showTimingsDto.getDate())) {
                showTimings.add(show.getTime());
            }
        }

        return showTimings;
    }

    public AvailableSeatsResponseDto getAvailableSeats(int showId) throws ShowDoesNotExists {
        Optional<Show> showOpt = showRepository.findById(showId);
        if (showOpt.isEmpty()) {
            throw new ShowDoesNotExists();
        }

        Show show = showOpt.get();
        List<ShowSeat> showSeats = show.getShowSeatList();
        List<String> availableSeats = new ArrayList<>();

        for (ShowSeat showSeat : showSeats) {
            if (showSeat.getIsAvailable()) {
                availableSeats.add(showSeat.getSeatNo());
            }
        }

        AvailableSeatsResponseDto response = new AvailableSeatsResponseDto();
        response.setAvailableSeats(availableSeats);
        return response;
    }

    public String movieHavingMostShows() {
        Integer movieId = showRepository.getMostShowsMovie();
        return movieRepository.findById(movieId).get().getMovieName();
    }

    public List<ShowResponseDto> getAvailableShows(AvailableShowsRequestDto availableShowsRequestDto) {
        List<Show> shows;

        // Build the query based on the provided criteria
        if (availableShowsRequestDto.getDate() != null) {
            if (availableShowsRequestDto.getMovieId() != null && availableShowsRequestDto.getTheaterId() != null) {
                // Filter by date, movie, and theater
                shows = showRepository.findByDateAndMovieIdAndTheaterId(
                        availableShowsRequestDto.getDate(),
                        availableShowsRequestDto.getMovieId(),
                        availableShowsRequestDto.getTheaterId());
            } else if (availableShowsRequestDto.getMovieId() != null) {
                // Filter by date and movie
                shows = showRepository.findByDateAndMovieId(
                        availableShowsRequestDto.getDate(),
                        availableShowsRequestDto.getMovieId());
            } else if (availableShowsRequestDto.getTheaterId() != null) {
                // Filter by date and theater
                shows = showRepository.findByDateAndTheaterId(
                        availableShowsRequestDto.getDate(),
                        availableShowsRequestDto.getTheaterId());
            } else {
                // Filter only by date
                shows = showRepository.findByDate(availableShowsRequestDto.getDate());
            }
        } else {
            // No date specified, return all shows (you might want to limit this)
            shows = showRepository.findAll();
        }

        // Convert Show entities to ShowResponseDto objects
        List<ShowResponseDto> showResponseDtos = new ArrayList<>();
        for (Show show : shows) {
            ShowResponseDto showResponseDto = new ShowResponseDto();
            showResponseDto.setShowId(show.getShowId());
            showResponseDto.setShowTime(show.getTime());
            showResponseDto.setShowDate(show.getDate());
            showResponseDto.setMovieName(show.getMovie().getMovieName());
            showResponseDto.setTheaterName(show.getTheater().getName());
            showResponseDto.setTheaterAddress(show.getTheater().getAddress());
            showResponseDtos.add(showResponseDto);
        }

        return showResponseDtos;
    }
}