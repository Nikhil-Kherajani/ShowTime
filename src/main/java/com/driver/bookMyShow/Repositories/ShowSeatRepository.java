package com.driver.bookMyShow.Repositories;

import com.driver.bookMyShow.Models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> { // Use Long here
    List<ShowSeat> findByShowShowId(Integer showId); // Method to find by showId
}