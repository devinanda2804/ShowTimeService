

package com.example.showTimeService.service;

/*import com.example.showTimeService.feign.MovieClient;*/
import com.example.showTimeService.model.Showtime;
import com.example.showTimeService.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ShowtimeService {

    @Autowired
    private ShowtimeRepository showtimeRepository;

   /* public Showtime addShowtime(int movieId, LocalDate showDate, LocalTime time) {
        Showtime showtime = new Showtime(movieId, showDate,time);
        return showtimeRepository.save(showtime);
    }*/
/*
   @Autowired
   private MovieClient movieClient; // Use only if Feign is enabled

    public Showtime addShowtime(int movieId, LocalDate showDate, LocalTime time) {
        if (!movieClient.checkIfMovieExists(movieId)) {
            throw new RuntimeException("Movie ID does not exist.");
        }
        Showtime showtime = new Showtime(movieId, showDate, time);
        return showtimeRepository.save(showtime);
    }
*/


    @Autowired
    private RestTemplate restTemplate;

    private final String MOVIE_SERVICE_URL = "http://localhost:8081/api/movies"; // URL of your MovieService

   /* public Showtime addShowtime(int movieId, LocalDate showDate, LocalTime time) {
        // Call MovieService to check if the movie exists
        String url = MOVIE_SERVICE_URL + "/" + movieId + "/exists"; // Construct the URL
        boolean movieExists = restTemplate.getForObject(url, Boolean.class); // Use RestTemplate to call the endpoint

        if (!movieExists) {
            throw new RuntimeException("Movie ID does not exist.");
        }

        Showtime showtime = new Showtime(movieId, showDate, time);
        return showtimeRepository.save(showtime);
    }*/
    public Showtime addShowtime(int movieId, LocalDate showDate, LocalTime time) {

        String url = MOVIE_SERVICE_URL + "/" + movieId + "/exists";

        boolean movieExists;
        try {
            movieExists = restTemplate.getForObject(url, Boolean.class);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Movie ID does not exist.");
        }

        if (!movieExists) {
            throw new RuntimeException("Movie ID does not exist.");
        }

        Showtime showtime = new Showtime(movieId, showDate, time);
        return showtimeRepository.save(showtime);
    }

    public List<Showtime> getShowtimesByMovieId(int movieId) {
        return showtimeRepository.findByMovieId(movieId);
    }

    public void deleteShowtime(int showtimeId) {
        showtimeRepository.deleteById(showtimeId);
    }
}
