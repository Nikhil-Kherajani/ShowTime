package com.driver.bookMyShow.Dtos.RequestDtos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AvailableShowsRequestDto {
    private LocalDate date;
    private Integer movieId;
    private Integer theaterId;
}