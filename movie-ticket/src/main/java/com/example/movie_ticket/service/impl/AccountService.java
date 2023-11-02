package com.example.movie_ticket.service.impl;

import com.example.movie_ticket.model.Account;
import com.example.movie_ticket.repository.IAccountRepo;
import com.example.movie_ticket.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepo accountRepo;
    @Override
    public Account findByUsername(String username) {
        return accountRepo.findByUsername(username);
    }
}