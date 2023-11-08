package com.example.movie_ticket.controller;

import com.example.movie_ticket.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private IPostService postService;

    @GetMapping
    public ModelAndView showBlogPage(){
        return new ModelAndView("/post/blog","posts",postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ModelAndView showDetailBlog(@PathVariable Long id){
        return new ModelAndView("post/blog-detail","post",postService.getPostById(id));
    }
}
