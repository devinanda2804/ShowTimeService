package com.example.showTimeService.service;

import com.example.showTimeService.feign.MovieClient;
import com.example.showTimeService.model.Showtime;
import com.example.showTimeService.repository.ShowtimeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ShowtimeServiceTest {

    @InjectMocks
    private ShowtimeService showtimeService;

    @Mock
    private ShowtimeRepository showtimeRepository;

    @Mock
    private MovieClient movieClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void addShowtimeTest() {

        Integer movieId = 100;
        LocalDate showDate = LocalDate.of(2024, 11, 15);
        LocalTime time = LocalTime.of(14, 30);
        Showtime expectedShowtime = new Showtime(movieId, showDate, time);

        when(movieClient.checkIfMovieExists(movieId)).thenReturn(ResponseEntity.ok(true));

        when(showtimeRepository.save(any(Showtime.class))).thenReturn(expectedShowtime);
        Showtime result = showtimeService.addShowtime(movieId, showDate, time);
        verify(showtimeRepository, times(1)).save(any(Showtime.class));

        assertNotNull(result);
        assertEquals(expectedShowtime.getMovieId(), result.getMovieId());
        assertEquals(expectedShowtime.getShowDate(), result.getShowDate());
        assertEquals(expectedShowtime.getTime(), result.getTime());
    }
    @Test
    public void getShowtimesByMovieIdTest() {

        int movieId = 100;
        List<Showtime> expectedShowtimes = Arrays.asList(
                new Showtime(movieId, LocalDate.of(2024, 11, 15), LocalTime.of(14, 30)),
                new Showtime(movieId, LocalDate.of(2024, 11, 16), LocalTime.of(18, 30))
        );


        when(showtimeRepository.findByMovieId(movieId)).thenReturn(expectedShowtimes);


        List<Showtime> result = showtimeService.getShowtimesByMovieId(movieId);


        verify(showtimeRepository, times(1)).findByMovieId(movieId);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedShowtimes, result);
    }


    @Test
    public void deleteShowtimeTest() {

        int showtimeId = 1;


        showtimeService.deleteShowtime(showtimeId);

        verify(showtimeRepository, times(1)).deleteById(showtimeId);
    }

    @Test
    public void existsByIdTest() {

        int movieId = 100;


        when(showtimeRepository.existsById(movieId)).thenReturn(true);


        boolean result = showtimeService.existsById(movieId);

        verify(showtimeRepository, times(1)).existsById(movieId);
        assertTrue(result);
    }






}
