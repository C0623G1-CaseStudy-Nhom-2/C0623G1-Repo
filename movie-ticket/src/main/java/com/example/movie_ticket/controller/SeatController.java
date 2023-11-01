package com.example.movie_ticket.controller;

import com.example.movie_ticket.service.ICategoryService;
import com.example.movie_ticket.service.IMovieService;
import com.example.movie_ticket.service.ISeatBookingService;
import com.example.movie_ticket.service.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/showtime")
public class SeatController {
    @Autowired
    private ISeatService seatService;
    @Autowired
    private IMovieService movieService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private ISeatBookingService seatBookingService;

    @GetMapping("/detail/{id}")
    public String showSeat(@PathVariable Long id, Model model){
        model.addAttribute("seats",seatService.showSeats());
        model.addAttribute("seatsActive",seatBookingService.getSeatsOrderedByShowTimes(id));
        return "seat";
    }

    @PostMapping("/check-out")
    public ModelAndView getSelectSeat(@RequestParam(name = "seat-select") String[] seats, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("seats",seats);
        return new ModelAndView("redirect:/check-out");
    }
}
