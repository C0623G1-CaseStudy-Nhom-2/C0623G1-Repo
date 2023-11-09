package com.example.movie_ticket.controller;

import com.example.movie_ticket.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public ModelAndView showBlogPage(@PageableDefault(value = 2,sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable){
        return new ModelAndView("/post/blog","posts",postService.getAllPosts(pageable));
    }

    @GetMapping("/{id}")
    public ModelAndView showDetailBlog(@PathVariable Long id){
        return new ModelAndView("post/blog-detail","post",postService.getPostById(id));
    }
}
