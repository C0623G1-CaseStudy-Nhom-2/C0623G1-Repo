package com.example.movie_ticket.service;

import com.example.movie_ticket.model.Customer;

public interface ICustomerService {
    Customer findCustomerbyId(Long id);
}
