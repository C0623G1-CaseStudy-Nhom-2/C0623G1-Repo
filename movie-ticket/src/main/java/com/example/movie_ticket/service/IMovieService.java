package com.example.movie_ticket.service;

import com.example.movie_ticket.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IMovieService {
    List<Movie> getAllMovie();
    Movie findMovieById(Long id);
    Map<Date, List<String>> showTimesMap(Long id);
    void saveMovie(Movie movie);
    Page<Movie> findAllMovie(Pageable pageable);
}
