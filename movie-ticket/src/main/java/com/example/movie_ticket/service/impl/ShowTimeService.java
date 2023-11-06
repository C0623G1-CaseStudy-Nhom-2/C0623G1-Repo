package com.example.movie_ticket.service.impl;

import com.example.movie_ticket.model.ShowTime;
import com.example.movie_ticket.repository.IShowTimeRepo;
import com.example.movie_ticket.service.IShowTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ShowTimeService implements IShowTimeService {
    @Autowired
    private IShowTimeRepo showTimeRepo;
    @Override
    public void saveNewShowtime(ShowTime showTime) {
        showTimeRepo.save(showTime);
    }

    @Override
    public void deleteShowtime(Long id) {
        showTimeRepo.deleteById(id);
    }

    @Override
    public Page<ShowTime> findAllShowtime(Pageable pageable) {
        return showTimeRepo.findAll(pageable);
    }

    @Override
    public ShowTime findShowtimeById(Long id) {
        if (showTimeRepo.findById(id).isPresent()) {
            return showTimeRepo.findById(id).get();
        }
        return null;
    }
}
