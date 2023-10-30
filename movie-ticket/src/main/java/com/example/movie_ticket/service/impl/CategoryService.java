package com.example.movie_ticket.service.impl;

import com.example.movie_ticket.model.Category;
import com.example.movie_ticket.repository.ICategoryRepo;
import com.example.movie_ticket.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepo categoryRepo;
    @Override
    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }
}
