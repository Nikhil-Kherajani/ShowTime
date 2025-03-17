package com.driver.bookMyShow.Controllers;

import com.driver.bookMyShow.Dtos.ResponseDtos.ShowSeatResponseDto;
import com.driver.bookMyShow.Services.ShowSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/show-seats")
public class ShowSeatController {

    @Autowired
    private ShowSeatService showSeatService;

    @GetMapping("/by-show/{showId}")
    public ResponseEntity<List<ShowSeatResponseDto>> getShowSeatsByShowId(@PathVariable Integer showId) {
        List<ShowSeatResponseDto> showSeats = showSeatService.getShowSeatsByShowId(showId);
        return new ResponseEntity<>(showSeats, HttpStatus.OK);
    }
}