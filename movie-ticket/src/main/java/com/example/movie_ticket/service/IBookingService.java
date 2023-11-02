package com.example.movie_ticket.service;

import com.example.movie_ticket.model.Booking;
import com.example.movie_ticket.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IBookingService {
    void saveBooking(Booking booking);
    Page<Booking> findBookingUsername(String username, Pageable pageable);
    Optional<Booking> findBookingById(Long id);
    void deleteBooking(Long id);
}
