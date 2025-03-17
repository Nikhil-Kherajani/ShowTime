package com.driver.bookMyShow.Controllers;

import com.driver.bookMyShow.Dtos.RequestDtos.AvailableShowsRequestDto;
import com.driver.bookMyShow.Dtos.RequestDtos.ShowEntryDto;
import com.driver.bookMyShow.Dtos.RequestDtos.ShowSeatEntryDto;
import com.driver.bookMyShow.Dtos.RequestDtos.ShowTimingsDto;
import com.driver.bookMyShow.Dtos.ResponseDtos.AvailableSeatsResponseDto;
import com.driver.bookMyShow.Dtos.ResponseDtos.ShowResponseDto;
import com.driver.bookMyShow.Exceptions.ShowDoesNotExists;
import com.driver.bookMyShow.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping("/addNew")
    public ResponseEntity<String> addShow(@RequestBody ShowEntryDto showEntryDto) {
        try {
            String result = showService.addShow(showEntryDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/associateSeats")
    public ResponseEntity<String> associateShowSeats(@RequestBody ShowSeatEntryDto showSeatEntryDto) {
        try {
            String result = showService.associateShowSeats(showSeatEntryDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/showTimingsOnDate")
    public ResponseEntity<List<LocalTime>> showTimingsOnDate(@RequestBody ShowTimingsDto showTimingsDto) {
        try {
            List<LocalTime> result = showService.showTimingsOnDate(showTimingsDto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/movieHavingMostShows")
    public ResponseEntity<String> movieHavingMostShows() {
        try {
            String movie = showService.movieHavingMostShows();
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/availableSeats")
    public ResponseEntity<?> getAvailableSeats(@RequestParam int showId) {
        try {
            AvailableSeatsResponseDto availableSeatsResponse = showService.getAvailableSeats(showId);
            return new ResponseEntity<>(availableSeatsResponse, HttpStatus.OK);
        } catch (ShowDoesNotExists e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/available")
    public ResponseEntity<List<ShowResponseDto>> getAvailableShows(@RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) Integer movieId,
            @RequestParam(required = false) Integer theaterId) {
        AvailableShowsRequestDto availableShowsRequestDto = new AvailableShowsRequestDto();
        availableShowsRequestDto.setDate(date);
        availableShowsRequestDto.setMovieId(movieId);
        availableShowsRequestDto.setTheaterId(theaterId);

        List<ShowResponseDto> availableShows = showService.getAvailableShows(availableShowsRequestDto);
        return new ResponseEntity<>(availableShows, HttpStatus.OK);
    }

}