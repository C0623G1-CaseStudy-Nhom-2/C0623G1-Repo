package com.example.movie_ticket.service.impl;

import com.example.movie_ticket.model.Customer;
import com.example.movie_ticket.repository.ICustomerRepo;
import com.example.movie_ticket.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepo customerRepo;
    @Override
    public Customer findCustomerbyId(Long id) {
        return customerRepo.findById(id).get();
    }
}
