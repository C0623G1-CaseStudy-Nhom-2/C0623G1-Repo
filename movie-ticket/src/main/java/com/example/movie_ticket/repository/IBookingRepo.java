package com.example.movie_ticket.repository;

import com.example.movie_ticket.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookingRepo extends JpaRepository<Booking,Long> {
}
