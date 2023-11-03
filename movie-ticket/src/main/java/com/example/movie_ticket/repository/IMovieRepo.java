package com.example.movie_ticket.repository;

import com.example.movie_ticket.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IMovieRepo extends JpaRepository<Movie, Long> {
    Page<Movie> findByTitleContaining(String nameMovie, Pageable pageable);

    Page<Movie> findByReleaseDate(String date, Pageable pageable);

    Page<Movie> findByIdAndTitleContaining(Long idMovie, String nameMovie, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * from movies ORDER BY category_id")
    Page<Movie> findAllOrderByCategory(Pageable pageable);
}
