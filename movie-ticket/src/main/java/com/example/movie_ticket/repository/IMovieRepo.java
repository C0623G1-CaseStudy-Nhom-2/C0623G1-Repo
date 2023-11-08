package com.example.movie_ticket.repository;

import com.example.movie_ticket.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IMovieRepo extends JpaRepository<Movie, Long> {
    Page<Movie> findByTitleContaining(String nameMovie, Pageable pageable);

    Page<Movie> findByReleaseDate(String date, Pageable pageable);

    Page<Movie> findByIdAndTitleContaining(Long idMovie, String nameMovie, Pageable pageable);
    Page<Movie> findMoviesByCategoryId(Long id, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * from movies ORDER BY category_id")
    Page<Movie> findAllOrderByCategory(Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * from movies where deleted = 0 ORDER BY release_date")
    Page<Movie> findAllOrderByDate(Pageable pageable);
    @Query(nativeQuery = true, value = "SELECT * FROM movies WHERE release_date >= :dateStart AND release_date <= :dateEnd AND deleted = 0 AND title LIKE CONCAT('%', :title, '%')")
    Page<Movie> findMovieByIdAndDateOrder(@Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd, @Param("title") String title, Pageable pageable);


}
