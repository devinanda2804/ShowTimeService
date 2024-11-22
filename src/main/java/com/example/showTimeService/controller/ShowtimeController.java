// src/main/java/com/example/showtimeservice/controller/ShowtimeController.java

package com.example.showTimeService.controller;

import com.example.showTimeService.dto.ShowtimeDTO;
import com.example.showTimeService.model.Showtime;
import com.example.showTimeService.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;



   @PostMapping("/movies/{movieId}/showtime")
   public ResponseEntity<?> addShowtime(@PathVariable int movieId, @RequestBody ShowtimeDTO showtimeRequest) {

           Showtime showtime = showtimeService.addShowtime(movieId, showtimeRequest.getShowDate(), showtimeRequest.getTime());
           return ResponseEntity.status(HttpStatus.CREATED).body(showtime);

   }


    @GetMapping("/movies/{movieId}/showtimes")
    public ResponseEntity<List<Showtime>> getShowtimesByMovieId(@PathVariable int movieId) {
        List<Showtime> showtimes = showtimeService.getShowtimesByMovieId(movieId);
        return ResponseEntity.ok(showtimes);
    }


    @DeleteMapping("/showtimes/{showtimeId}")
    public ResponseEntity<Void> deleteShowtime(@PathVariable int showtimeId) {
        showtimeService.deleteShowtime(showtimeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{showTimeId}/exists")
    public ResponseEntity<Boolean> checkIfShowTimeExists(@PathVariable int showTimeId) {
        boolean exists = showtimeService.existsById(showTimeId);
        return ResponseEntity.ok(exists);
    }

}
