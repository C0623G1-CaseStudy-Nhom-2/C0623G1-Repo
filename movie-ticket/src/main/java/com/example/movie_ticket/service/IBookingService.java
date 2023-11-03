package com.example.movie_ticket.service;

import com.example.movie_ticket.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBookingService {
    Page<Booking> showAllBooking(Pageable pageable,String phone,String name);
    Booking findByIdBooking(Long id);
    void deleteBooking(Long id);
    Booking findById(Long id);
    void updateBooking(Booking booking);
}
