package com.example.movie_ticket.repository;

import com.example.movie_ticket.model.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IShowTimeRepo extends JpaRepository<ShowTime, Long> {
}
