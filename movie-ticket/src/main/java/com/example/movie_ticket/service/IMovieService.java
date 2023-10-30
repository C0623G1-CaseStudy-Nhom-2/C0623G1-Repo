package com.example.movie_ticket.service;

import com.example.movie_ticket.model.Movie;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IMovieService {
    List<Movie> getAllMovie();
    Movie findMovieById(Long id);
    Map<Date, List<String>> showTimesMap(Long id);
}
