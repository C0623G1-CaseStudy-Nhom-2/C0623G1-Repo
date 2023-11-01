package com.example.movie_ticket.service;


import com.example.movie_ticket.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> getAllCategory();
    void addCategory(Category category);
    Optional<Category> findById(Long id);
    void deleteCategory(Long id);
}
