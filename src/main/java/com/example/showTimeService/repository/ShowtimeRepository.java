package com.example.showTimeService.repository;


import com.example.showTimeService.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Integer> {

    List<Showtime> findByMovieId(int movieId);
}