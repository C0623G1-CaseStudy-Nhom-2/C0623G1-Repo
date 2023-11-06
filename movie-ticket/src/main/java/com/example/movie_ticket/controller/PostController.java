package com.example.movie_ticket.controller;

import com.example.movie_ticket.model.Post;
import com.example.movie_ticket.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dashboard/posts")
public class PostController {
    @Autowired
    private IPostService postService;

    @GetMapping
    public ModelAndView getAllPosts() {
       return new ModelAndView("/post/dashboard-admin-post","posts",postService.getAllPosts());
    }

    @GetMapping("/add")
    public ModelAndView showCreatePost() {
        return new ModelAndView("/post/create","post",new Post());
    }

}
