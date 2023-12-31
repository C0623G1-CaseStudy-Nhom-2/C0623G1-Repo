package com.example.movie_ticket.service.impl;

import com.example.movie_ticket.model.Movie;
import com.example.movie_ticket.repository.IMovieRepo;
import com.example.movie_ticket.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MovieService implements IMovieService {
    @Autowired
    private IMovieRepo movieRepo;

    @Override
    public List<Movie> getAllMovie() {
        return movieRepo.findAll();
    }

    @Override
    public Movie findMovieById(Long id) {
        return movieRepo.findById(id).get();
    }

    @Override
    public Map<Date, List<String>> showTimesMap(Long id) {
        return null;
    }
}
