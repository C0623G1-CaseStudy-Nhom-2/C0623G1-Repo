package com.example.movie_ticket.controller;

import com.example.movie_ticket.service.ICategoryService;
import com.example.movie_ticket.service.IMovieService;
import com.example.movie_ticket.service.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/showtime")
public class SeatController {
    @Autowired
    private ISeatService seatService;
    @Autowired
    private IMovieService movieService;
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/detail/{id}")
    public ModelAndView showSeat(){
        return new ModelAndView("seat","seats",seatService.showSeats());
    }

    @PostMapping("/showtime")
    public ModelAndView getSelectSeat(@RequestParam(name = "seat-select") String[] seats, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("seats",seats);
        return new ModelAndView("redirect:/check-out");
    }
}
