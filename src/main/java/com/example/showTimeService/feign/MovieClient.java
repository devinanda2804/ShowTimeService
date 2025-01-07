/*
package com.example.showTimeService.feign;

import com.example.showTimeService.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(name = "movie-service", url = "http://localhost:8081/api/movies", configuration = FeignClientConfig.class)
public interface MovieClient {

     @GetMapping("/{movieId}/exists")
    public ResponseEntity<Boolean> checkIfMovieExists(@PathVariable int movieId);
}
*/
