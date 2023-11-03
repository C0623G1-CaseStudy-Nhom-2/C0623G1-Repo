package com.example.movie_ticket.repository;

import com.example.movie_ticket.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMovieRepo extends JpaRepository<Movie,Long> {
    List<Movie> findAllByTitleContaining(String title);
    Movie findByTitle(String title);
}
