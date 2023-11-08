package com.example.movie_ticket.service;

import com.example.movie_ticket.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IPostService {
    List<Post> getAllPosts();
    Page<Post> getAllPosts(Pageable pageable);
    Post getPostById(Long id);
    Post savePost(Post post);
    void deletePost(Long id);
}
