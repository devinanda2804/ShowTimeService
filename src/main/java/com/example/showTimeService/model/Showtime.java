package com.example.showTimeService.model;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "showTimes")
@Data

@RequiredArgsConstructor
public class Showtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int showtimeId;

    @Column(nullable = false)
    private int movieId;

    @Column(nullable = false)
    private LocalDate showDate;

    @Column(nullable = false)
    private LocalTime time;

    public Showtime(int movieId, LocalDate showDate, LocalTime time) {
        this.movieId = movieId;
        this.showDate = showDate;
        this.time = time;
    }


}
