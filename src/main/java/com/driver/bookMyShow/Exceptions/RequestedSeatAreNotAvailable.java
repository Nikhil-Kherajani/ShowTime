package com.driver.bookMyShow.Exceptions;

public class RequestedSeatAreNotAvailable extends RuntimeException {
    public RequestedSeatAreNotAvailable(String message) {
        super(message);
    }
}
