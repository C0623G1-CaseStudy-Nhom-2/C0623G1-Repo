package com.example.movie_ticket.repository;

import com.example.movie_ticket.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepo extends JpaRepository<Category,Long> {
}
