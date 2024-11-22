package com.example.showTimeService.feign;

import com.example.showTimeService.feign.MovieClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MovieClientTest {

    @Autowired
    private MovieClient movieClient;

    @MockBean
    private MovieClient mockMovieClient;

    @Test
    public void checkIfMovieExistsTest() {

        int movieId = 100;
        when(mockMovieClient.checkIfMovieExists(movieId)).thenReturn(ResponseEntity.ok(true));

        ResponseEntity<Boolean> response = mockMovieClient.checkIfMovieExists(movieId);

        assertTrue(response.getBody());
    }
}
