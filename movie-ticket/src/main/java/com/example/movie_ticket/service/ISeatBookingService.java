package com.example.movie_ticket.service;

import java.util.List;

public interface ISeatBookingService {
    List<String> getSeatsOrderedByShowTimes(Long showTimeId);
    void saveSeatBooking(SeatBooking seatBooking);
    List<SeatBooking> getAll();
}
