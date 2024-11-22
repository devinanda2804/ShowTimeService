package com.example.showTimeService.repository;

import com.example.showTimeService.model.Showtime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class ShowtimeRepositoryTest {

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Test
    public void testFindByMovieId() {
        Showtime showtime1 = new Showtime(100, LocalDate.of(2024, 11, 15), LocalTime.of(14, 30));
        Showtime showtime2 = new Showtime(100, LocalDate.of(2024, 11, 16), LocalTime.of(18, 30));
        Showtime showtime3 = new Showtime(101, LocalDate.of(2024, 11, 15), LocalTime.of(10, 0));

        showtimeRepository.save(showtime1);
        showtimeRepository.save(showtime2);
        showtimeRepository.save(showtime3);


        List<Showtime> result = showtimeRepository.findByMovieId(100);

        assertEquals(2, result.size());
        assertTrue(result.contains(showtime1));
        assertTrue(result.contains(showtime2));
    }
}
