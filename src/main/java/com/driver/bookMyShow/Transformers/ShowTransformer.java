package com.driver.bookMyShow.Transformers;

import com.driver.bookMyShow.Dtos.RequestDtos.ShowEntryDto;
import com.driver.bookMyShow.Models.Show;

public class ShowTransformer {
    public static Show showDtoToShow(ShowEntryDto showEntryDto) {
        return Show.builder()
                .time(showEntryDto.getShowStartTime())
                .date(showEntryDto.getShowDate())
                .build();
    }
}