package com.example.movie_ticket.repository;

import com.example.movie_ticket.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
public interface IMovieRepo extends JpaRepository<Movie,Long> {
}
