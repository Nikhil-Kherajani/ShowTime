package com.driver.bookMyShow.Dtos.RequestDtos;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowEntryDto {

    private LocalTime showStartTime;
    private LocalDate showDate;
    private Integer theaterId;
    private Integer movieId;
}