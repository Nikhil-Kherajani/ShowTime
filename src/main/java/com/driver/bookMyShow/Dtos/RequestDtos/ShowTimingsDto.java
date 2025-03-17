package com.driver.bookMyShow.Dtos.RequestDtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ShowTimingsDto {
    private LocalDate date;
    private int theaterId;
    private int movieId;
}