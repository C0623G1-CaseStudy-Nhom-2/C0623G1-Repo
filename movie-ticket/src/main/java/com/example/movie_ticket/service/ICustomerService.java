package com.example.movie_ticket.service;

import com.example.movie_ticket.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> getAllCustomer();

    void deleteCustomerById(Long id);

    Customer getCustomerById(Long id);

    void updateCustomer(Customer customer);
}
