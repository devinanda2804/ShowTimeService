package com.example.showTimeService.controller;

import com.example.showTimeService.feign.MovieClient;
import com.example.showTimeService.model.Showtime;
import com.example.showTimeService.dto.ShowtimeDTO;
import com.example.showTimeService.repository.ShowtimeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShowtimeControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private MovieClient movieClient;

    @MockBean
    private ShowtimeRepository showtimeRepository;

    @BeforeEach
    public void setup() {
        testRestTemplate = testRestTemplate.withBasicAuth("admin", "admin");
    }

    @Test
    public void testAddShowtime() {
        int movieId = 101;

        ShowtimeDTO showtimeRequest = new ShowtimeDTO();
        showtimeRequest.setShowDate(LocalDate.of(2024, 11, 20));
        showtimeRequest.setTime(LocalTime.parse("15:30"));

        Showtime mockShowtime = new Showtime();
        mockShowtime.setMovieId(movieId);
        mockShowtime.setShowDate(LocalDate.of(2024, 11, 20));
        mockShowtime.setTime(LocalTime.parse("15:30"));


        Mockito.when(movieClient.checkIfMovieExists(movieId)).thenReturn(ResponseEntity.ok(true));
        Mockito.when(showtimeRepository.save(Mockito.any(Showtime.class))).thenReturn(mockShowtime);
        Mockito.when(showtimeRepository.findAll()).thenReturn(Arrays.asList(mockShowtime));

        // Act
        ResponseEntity<Showtime> response = testRestTemplate.postForEntity(
                "/api/admin/movies/{movieId}/showtime",
                showtimeRequest,
                Showtime.class,
                movieId
        );

        // Assert
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(movieId, response.getBody().getMovieId());
        assertEquals(LocalDate.of(2024, 11, 20), response.getBody().getShowDate());
        assertEquals(LocalTime.parse("15:30"), response.getBody().getTime());
    }

    @Test
    public void testGetShowtimesByMovieId() {
        int movieId = 101;

        Showtime mockShowtime = new Showtime();
        mockShowtime.setMovieId(movieId);
        mockShowtime.setShowDate(LocalDate.of(2024, 11, 20));
        mockShowtime.setTime(LocalTime.parse("15:30"));

        // Mock Repository
        Mockito.when(showtimeRepository.findByMovieId(movieId)).thenReturn(Arrays.asList(mockShowtime));

        ResponseEntity<List> response = testRestTemplate.getForEntity(
                "/api/admin/movies/{movieId}/showtimes",
                List.class,
                movieId
        );

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().size() > 0);
    }

    @Test
    public void testDeleteShowtime() {
        int showtimeId = 1;

        // Mock Repository
        Mockito.when(showtimeRepository.existsById(showtimeId)).thenReturn(true);
        Mockito.doNothing().when(showtimeRepository).deleteById(showtimeId);

        ResponseEntity<Void> response = testRestTemplate.exchange(
                "/api/admin/showtimes/{showtimeId}",
                HttpMethod.DELETE,
                null,
                Void.class,
                showtimeId
        );

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testCheckIfShowtimeExists() {
        int showtimeId = 1;

        // Mock Repository
        Mockito.when(showtimeRepository.existsById(showtimeId)).thenReturn(true);

        ResponseEntity<Boolean> response = testRestTemplate.getForEntity(
                "/api/admin/{showtimeId}/exists",
                Boolean.class,
                showtimeId
        );

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());
    }
}
