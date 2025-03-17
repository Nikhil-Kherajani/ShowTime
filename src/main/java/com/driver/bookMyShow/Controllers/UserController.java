package com.driver.bookMyShow.Controllers;

import com.driver.bookMyShow.Dtos.RequestDtos.LoginRequestDto;
import com.driver.bookMyShow.Dtos.RequestDtos.UserEntryDto;
import com.driver.bookMyShow.Dtos.ResponseDtos.AuthResponseDto;
import com.driver.bookMyShow.Dtos.ResponseDtos.TicketResponseDto;
import com.driver.bookMyShow.Services.UserService;
import com.driver.bookMyShow.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

  

    @GetMapping("/userdetailsfromemail/{email}")
    public ResponseEntity<User> getUserDetailsFromEmail(@PathVariable String email) {
        try {
            User user = userService.getUserDetailsFromEmailFunction(email);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> registerUser(@RequestBody UserEntryDto userEntryDto) {
        try {
            AuthResponseDto response = userService.registerUser(userEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new AuthResponseDto(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> loginUser(@RequestBody LoginRequestDto loginRequest) {
        try {
            AuthResponseDto response = userService.loginUser(loginRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new AuthResponseDto(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/allTickets/{userId}")
    public ResponseEntity<List<TicketResponseDto>> allTickets(@PathVariable Integer userId) {
        try {
            List<TicketResponseDto> result = userService.allTickets(userId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
