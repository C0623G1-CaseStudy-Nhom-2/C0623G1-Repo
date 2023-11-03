package com.example.movie_ticket.service.impl;

import com.example.movie_ticket.model.Booking;
import com.example.movie_ticket.model.Customer;
import com.example.movie_ticket.repository.IBookingRepo;
import com.example.movie_ticket.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingServiceImpl implements IBookingService {
    @Autowired
    private IBookingRepo bookingRepo;
    @Override
    public void saveBooking(Booking booking) {
        bookingRepo.save(booking);
    }

    @Override
    public Page<Booking> findBookingUsername(String username, Pageable pageable) {
        return bookingRepo.findBookingUsername(username, pageable);
    }

    @Override
    public Optional<Booking> findBookingById(Long id) {
        return bookingRepo.findById(id);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepo.delete(findBookingById(id).get());
    }
    @Override
    public Page<Booking> showAllBooking(Pageable pageable, String phone, String name) {
        return bookingRepo.showAllBooking(pageable, "%"+phone+"%","%"+ name+"%");
    }

    @Override
    public Booking findByIdBooking(Long id) {
        return bookingRepo.findById(id).get();
    }

    @Override
    public void cancelBooking(Long id) {
        bookingRepo.cancelBooking(id);
    }

    @Override
    public Booking findById(Long id) {
        return bookingRepo.findById(id).get();
    }

    @Override
    public void updateBooking(Booking booking) {
        bookingRepo.save(booking);
    }
}
