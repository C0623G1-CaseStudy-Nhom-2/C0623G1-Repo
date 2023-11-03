package com.example.movie_ticket.service.impl;

import com.example.movie_ticket.model.Movie;
import com.example.movie_ticket.model.ShowTime;
import com.example.movie_ticket.repository.IShowTimeRepo;
import com.example.movie_ticket.service.IShowTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowTimeService implements IShowTimeService {
    @Autowired
    private IShowTimeRepo showTimeRepo;

    @Override
    public List<ShowTime> getAllShowTime() {
        return showTimeRepo.findAll();
    }

}
