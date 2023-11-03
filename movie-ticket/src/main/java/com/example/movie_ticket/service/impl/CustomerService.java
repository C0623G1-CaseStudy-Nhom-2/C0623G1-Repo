package com.example.movie_ticket.service.impl;

import com.example.movie_ticket.model.Customer;
import com.example.movie_ticket.repository.ICustomerRepo;
import com.example.movie_ticket.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepo customerRepo;
    @Override
    public List<Customer> getAllCustomer() {
        return customerRepo.findAllCusromer();
    }
    @Override
    public void deleteCustomerById(Long id) {
        customerRepo.deleteCustomerById(id);
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepo.save(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepo.findById(id).get();
    }
    @Override
    public Customer findCustomerbyId(Long id) {
        return customerRepo.findById(id).get();
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepo.save(customer);
    }
}
