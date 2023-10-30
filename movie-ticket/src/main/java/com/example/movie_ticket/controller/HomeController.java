package com.example.movie_ticket.controller;

import com.example.movie_ticket.service.ICategoryService;
import com.example.movie_ticket.service.IMovieService;
import com.example.movie_ticket.service.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("user")
@RequestMapping
public class HomeController {
    @Autowired
    private ISeatService seatService;
    @Autowired
    private IMovieService movieService;
    @Autowired
    private ICategoryService categoryService;
    @GetMapping
    public String showHome(Model model) {
        model.addAttribute("movies",movieService.getAllMovie());
        model.addAttribute("categories",categoryService.getAllCategory());
        return "index";
    }
    @GetMapping("/check-out")
    public ModelAndView showCheckOut(){
        return new ModelAndView("checkout");
    }

}
