package com.example.movie_ticket.repository;

import com.example.movie_ticket.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IBookingRepo extends JpaRepository<Booking,Long> {
    @Query(value = "select bk.id,cus.full_name,cus.phone_number,cus.email,st.movie_id,st.price,st.show_date,bk.code_booking,bk.customer_id,bk.showtime_id,bk.is_deleted " +
            "            from bookings bk " +
            "            join customer cus on cus.id = bk.customer_id " +
            "            join show_time st on st.id = bk.showtime_id " +
            "            where is_deleted = 0 and cus.phone_number like:phone and cus.full_name like:name" +
            "            order by ABS(DATEDIFF(CURDATE(), st.show_date)) ",nativeQuery = true)
    Page<Booking> showAllBooking(Pageable pageable, @Param("phone") String phone, @Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = " update bookings set is_deleted" +
            " where bookings.id =:id",nativeQuery = true)
    void deleteById(@Param("id") Long id);
}
