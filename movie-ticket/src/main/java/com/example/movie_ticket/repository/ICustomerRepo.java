package com.example.movie_ticket.repository;

import com.example.movie_ticket.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepo extends JpaRepository<Customer,Long> {

}
