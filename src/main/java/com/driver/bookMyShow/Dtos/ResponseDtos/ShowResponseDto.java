package com.driver.bookMyShow.Dtos.ResponseDtos;

import lombok.Data;
import java.time.LocalTime;
import java.time.LocalDate;

@Data
public class ShowResponseDto {
    private int showId;
    private LocalTime showTime;
    private LocalDate showDate;
    private String movieName;
    private String theaterName;
    private String theaterAddress;
}