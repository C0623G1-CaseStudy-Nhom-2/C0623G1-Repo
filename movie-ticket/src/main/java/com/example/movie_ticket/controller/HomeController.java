package com.example.movie_ticket.controller;

import com.example.movie_ticket.service.ICategoryService;
import com.example.movie_ticket.service.IMovieService;
import com.example.movie_ticket.service.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/dashboard-user")
    public ModelAndView showDashboardUser(){
        return new ModelAndView("dashboard-user");
    }

    @GetMapping("/dashboard-admin")
    public ModelAndView showDashboardAdmin(){
        return new ModelAndView("dashboard-admin");
    }

    @GetMapping("/dashboard-admin-order")
    public ModelAndView showDashboardAdminOrder(){
        return new ModelAndView("dashboard-admin-table");
    }
}
