package com.example.movie_ticket.service;

import com.example.movie_ticket.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> getAllCustomer();
    void deleteCustomerById(Long id);
    void saveCustomer(Customer customer);
    Customer getCustomerById(Long id);
    Customer findCustomerbyId(Long id);
}
