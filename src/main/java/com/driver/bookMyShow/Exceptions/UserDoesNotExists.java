package com.driver.bookMyShow.Exceptions;

public class UserDoesNotExists extends RuntimeException {
    public UserDoesNotExists() {
        super("User Does Not Exists");
    }

    public UserDoesNotExists(String message) {
        super(message);
    }
}
