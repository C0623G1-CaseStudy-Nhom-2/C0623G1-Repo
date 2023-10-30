package com.example.movie_ticket.repository;

import com.example.movie_ticket.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface IMovieRepo extends JpaRepository<Movie,Long> {
    Page<Movie> findByTitleContaining(String keyword, Pageable pageable);
}
