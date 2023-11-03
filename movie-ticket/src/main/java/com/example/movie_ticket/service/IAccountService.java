package com.example.movie_ticket.service;

import com.example.movie_ticket.model.Account;

public interface IAccountService {
    Account findByUsername(String username);
    void signUpAccount(Account account);
}
