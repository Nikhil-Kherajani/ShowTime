package com.driver.bookMyShow.Dtos.ResponseDtos;

import lombok.Data;
import java.util.List;

@Data
public class AvailableSeatsResponseDto {
    private List<String> availableSeats;
}