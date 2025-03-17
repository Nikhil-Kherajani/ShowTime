package com.driver.bookMyShow.Transformers;

import com.driver.bookMyShow.Dtos.ResponseDtos.ShowSeatResponseDto;
import com.driver.bookMyShow.Models.ShowSeat;

public class ShowSeatTransformer {

    public static ShowSeatResponseDto convertEntityToDto(ShowSeat showSeat) {
        ShowSeatResponseDto showSeatResponseDto = new ShowSeatResponseDto();

        showSeatResponseDto.setId(showSeat.getId());
        showSeatResponseDto.setSeatNo(showSeat.getSeatNo());
        showSeatResponseDto.setSeatType(showSeat.getSeatType());
        showSeatResponseDto.setAvailable(showSeat.getIsAvailable());
        showSeatResponseDto.setFoodContains(showSeat.getIsFoodContains());
        showSeatResponseDto.setPrice(showSeat.getPrice());

        return showSeatResponseDto;
    }
}
