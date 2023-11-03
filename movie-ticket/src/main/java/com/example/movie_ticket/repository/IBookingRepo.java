package com.example.movie_ticket.repository;

import com.example.movie_ticket.model.Booking;
import com.example.movie_ticket.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IBookingRepo extends JpaRepository<Booking,Long> {
    @Query(value = "select * from bookings bk join customer c on bk.customer_id = c.id join accounts a on c.account_id = a.id where a.username = :param1",
            nativeQuery = true)
    Page<Booking> findBookingUsername(@Param("param1") String username, Pageable pageable);
}
