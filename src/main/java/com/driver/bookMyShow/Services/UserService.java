package com.driver.bookMyShow.Services;

import com.driver.bookMyShow.Dtos.RequestDtos.LoginRequestDto;
import com.driver.bookMyShow.Dtos.RequestDtos.UserEntryDto;
import com.driver.bookMyShow.Dtos.ResponseDtos.AuthResponseDto;
import com.driver.bookMyShow.Dtos.ResponseDtos.TicketResponseDto;
import com.driver.bookMyShow.Exceptions.UserAlreadyExistsWithEmail;
import com.driver.bookMyShow.Exceptions.UserDoesNotExists;
import com.driver.bookMyShow.Models.Ticket;
import com.driver.bookMyShow.Models.User;
import com.driver.bookMyShow.Repositories.UserRepository;
import com.driver.bookMyShow.Security.JwtTokenUtil;
import com.driver.bookMyShow.Transformers.TicketTransformer;
import com.driver.bookMyShow.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public AuthResponseDto registerUser(UserEntryDto userEntryDto) throws UserAlreadyExistsWithEmail {
        Optional<User> optionalUser = userRepository.findByEmailId(userEntryDto.getEmailId());

        if(optionalUser.isPresent())
            throw new UserAlreadyExistsWithEmail("Email Id already registered!");

        User user = User.builder()
                .name(userEntryDto.getName())
                .age(userEntryDto.getAge())
                .emailId(userEntryDto.getEmailId())
                .mobileNo(userEntryDto.getMobileNo())
                .address(userEntryDto.getAddress())
                .gender(userEntryDto.getGender())
                .password(passwordEncoder.encode(userEntryDto.getPassword()))
                .ticketList(new ArrayList<>())
                .build();

        userRepository.save(user);

        String token = jwtTokenUtil.generateToken(user.getEmailId());
        return new AuthResponseDto(token, "User registered successfully");
    }

    public AuthResponseDto loginUser(LoginRequestDto loginRequest) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmailId(loginRequest.getEmail());

        if(optionalUser.isEmpty())
            throw new UserDoesNotExists("User does not exist with this email!");

        User user = optionalUser.get();
        
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
            throw new Exception("Invalid password!");

        String token = jwtTokenUtil.generateToken(user.getEmailId());
        return new AuthResponseDto(token, "Login successful");
    }

    public List<TicketResponseDto> allTickets(Integer userId) throws UserDoesNotExists {
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty())
            throw new UserDoesNotExists("User does not exist!");

        User user = optionalUser.get();
        List<Ticket> ticketList = user.getTicketList();

        List<TicketResponseDto> ticketResponseDtos = new ArrayList<>();
        for(Ticket ticket : ticketList) {
            TicketResponseDto ticketResponseDto = TicketTransformer.returnTicket(ticket.getShow(), ticket);
            ticketResponseDtos.add(ticketResponseDto);
        }
        return ticketResponseDtos;
    }

    public User getUserDetailsFromEmailFunction(String email) throws UserDoesNotExists {
        Optional<User> optionalUser = userRepository.findByEmailId(email);

        if(optionalUser.isEmpty())
            throw new UserDoesNotExists("User does not exist with this email!");

        User user = optionalUser.get();
        return user;
    }
}
