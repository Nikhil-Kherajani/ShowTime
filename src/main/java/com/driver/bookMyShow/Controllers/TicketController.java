package com.driver.bookMyShow.Controllers;

import com.driver.bookMyShow.EmailProducer;
import com.driver.bookMyShow.Dtos.RequestDtos.TicketEntryDto;
import com.driver.bookMyShow.Dtos.ResponseDtos.EmailEvent;
import com.driver.bookMyShow.Dtos.ResponseDtos.TicketResponseDto;
import com.driver.bookMyShow.Exceptions.RequestedSeatAreNotAvailable;
import com.driver.bookMyShow.Exceptions.ShowDoesNotExists;
import com.driver.bookMyShow.Exceptions.UserDoesNotExists;
import com.driver.bookMyShow.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private EmailProducer emailProducer;

    // @PostMapping("/book")
    // public ResponseEntity<?> ticketBooking(@RequestBody TicketEntryDto
    // ticketEntryDto) {
    // try {
    // TicketResponseDto result = ticketService.ticketBooking(ticketEntryDto);
    // return new ResponseEntity<>(result, HttpStatus.CREATED);
    // } catch (UserDoesNotExists e) {
    // return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    // } catch (ShowDoesNotExists e) {
    // return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    // } catch (RequestedSeatAreNotAvailable e) {
    // return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    // } catch (Exception e) {
    // return new ResponseEntity<>("An error occurred",
    // HttpStatus.INTERNAL_SERVER_ERROR);
    // }
    // }

    @PostMapping("/book")
    public ResponseEntity<?> ticketBooking(@RequestBody TicketEntryDto ticketEntryDto) {
        EmailEvent emailEvent = new EmailEvent(ticketEntryDto.getUserId() + "",
                "Ticket Details Here: " + ticketEntryDto.getShowId());
        emailProducer.sendEmailEvent(emailEvent);

        return ResponseEntity.ok("Ticket booked successfully!");
    }

}