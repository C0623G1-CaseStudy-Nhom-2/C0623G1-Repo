package com.example.movie_ticket.service.impl;

import com.example.movie_ticket.model.ShowTime;
import com.example.movie_ticket.repository.IShowTimeRepo;
import com.example.movie_ticket.service.IShowTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowTimeService implements IShowTimeService {
    @Autowired
    private IShowTimeRepo showTimeRepo;

    @Override
    public ShowTime getShowTimeById(Long id) {
        return showTimeRepo.findById(id).get();
    }
}
