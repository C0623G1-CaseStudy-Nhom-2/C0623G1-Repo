package com.example.movie_ticket.repository;

import com.example.movie_ticket.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ICustomerRepo extends JpaRepository<Customer,Long> {

    @Transactional
    @Modifying
    @Query(value = "update customer set statis = 0 where customer.id=:id ",nativeQuery = true)
    void deleteCustomerById(@Param("id") Long id);

    @Query(value = "select * from customer",nativeQuery = true)
    List<Customer> findAllCusromer();

    @Query(value = "select * from customer where statis = 1 and customer.full_name like :name and customer.phone_number like :phone",nativeQuery = true)
    Page<Customer> getAllCustomerPageable(Pageable pageable , @Param("name") String name,@Param("phone")String phone);


    @Query(value = "select customer.id , customer.address,customer.birthday,customer.email,customer.full_name,customer.phone_number,customer.account_id,customer.statis,customer.id_card" +
            " from customer " +
            "join bookings on customer.id = bookings.customer_id " +
            "join show_time on bookings.showtime_id = show_time.id where customer.id =:id and show_time.show_date >= current_date() " +
            "order by show_time.show_date asc " +
            "limit  1 ;",nativeQuery = true)
    Customer getCustomerDelete(@Param("id") Long id);
}
