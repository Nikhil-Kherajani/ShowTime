package com.driver.bookMyShow.Dtos.ResponseDtos;

import lombok.Data;

@Data
public class EmailEvent {
    private String userEmail;
    private String ticketDetails;

    // Constructor, Getters & Setters
    public EmailEvent(String userEmail, String ticketDetails) {
        this.userEmail = userEmail;
        this.ticketDetails = ticketDetails;
    }

}
