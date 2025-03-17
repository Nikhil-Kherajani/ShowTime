package com.driver.bookMyShow.Services;

import com.driver.bookMyShow.Dtos.ResponseDtos.ShowSeatResponseDto;
import com.driver.bookMyShow.Models.ShowSeat;
import com.driver.bookMyShow.Repositories.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowSeatService {

    @Autowired
    private ShowSeatRepository showSeatRepository;

    public List<ShowSeatResponseDto> getShowSeatsByShowId(Integer showId) {

        List<ShowSeat> showSeats = showSeatRepository.findByShowShowId(showId);
        List<ShowSeatResponseDto> responseDtos = new ArrayList<>();

        for (ShowSeat showSeat : showSeats) {
            ShowSeatResponseDto responseDto = new ShowSeatResponseDto();
            responseDto.setId(showSeat.getId());
            responseDto.setAvailable(showSeat.getIsAvailable());
            responseDto.setFoodContains(showSeat.getIsFoodContains());
            responseDto.setPrice(showSeat.getPrice());
            responseDto.setSeatNo(showSeat.getSeatNo());
            responseDto.setSeatType(showSeat.getSeatType());
            responseDtos.add(responseDto);
        }

        return responseDtos;
    }
}
