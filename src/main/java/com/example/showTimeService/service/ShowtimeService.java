

package com.example.showTimeService.service;

/*import com.example.showTimeService.feign.MovieClient;*/
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

 /*  @Autowired
   private MovieClient movieClient;*/


   public Showtime addShowtime(int movieId, LocalDate showDate, LocalTime time) {
      /* Boolean movieExists = movieClient.checkIfMovieExists(movieId).getBody();

       if (movieExists == null || !movieExists) {
           throw new RuntimeException("Movie ID does not exist.");
       }*/

       Showtime showtime = new Showtime(movieId, showDate, time);
       return showtimeRepository.save(showtime);
   }




    public List<Showtime> getShowtimesByMovieId(int movieId) {
        return showtimeRepository.findByMovieId(movieId);
    }

    public void deleteShowtime(int showtimeId) {
        showtimeRepository.deleteById(showtimeId);

    }

    public boolean existsById(int movieId) {
        return showtimeRepository.existsById(movieId);
    }
}
