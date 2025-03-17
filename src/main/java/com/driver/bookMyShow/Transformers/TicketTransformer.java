package com.driver.bookMyShow.Transformers;

import com.driver.bookMyShow.Dtos.ResponseDtos.TicketResponseDto;
import com.driver.bookMyShow.Models.Show;
import com.driver.bookMyShow.Models.Ticket;

public class TicketTransformer {
    public static TicketResponseDto returnTicket(Show show, Ticket ticket) {

        if (show == null || ticket == null) {
            return null; // Or handle the error in a way that makes sense for your application
        }

        String movieName = (show.getMovie() != null) ? show.getMovie().getMovieName() : null;
        String theaterName = (show.getTheater() != null) ? show.getTheater().getName() : null;
        String theaterAddress = (show.getTheater() != null) ? show.getTheater().getAddress() : null;

        return TicketResponseDto.builder()
                .bookedSeats(ticket.getBookedSeats())
                .totalPrice(ticket.getTotalTicketsPrice())
                .date(show.getDate())
                .time(show.getTime())
                .movieName(movieName)
                .theaterName(theaterName)
                .address(theaterAddress)
                .build();
    }
}