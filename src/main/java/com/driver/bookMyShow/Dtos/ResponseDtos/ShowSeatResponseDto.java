package com.driver.bookMyShow.Dtos.ResponseDtos;

import com.driver.bookMyShow.Enums.SeatType;
import lombok.Data;

@Data
public class ShowSeatResponseDto {
    private Integer id; // Use Long for consistency with entity IDs
    private boolean isAvailable;
    private boolean isFoodContains;
    private int price;
    private String seatNo;
    private SeatType seatType;
    // Don't include showShowId in the response DTO. The client already knows it.
}