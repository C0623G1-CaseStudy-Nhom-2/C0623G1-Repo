package com.example.movie_ticket.controller.dashboard;

import com.example.movie_ticket.model.ShowTime;
import com.example.movie_ticket.service.IShowTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dashboard/showtime")
public class ShowtimeDashboardController {
    @Autowired
    private IShowTimeService showTimeService;

    @GetMapping
    public ModelAndView showShowtimeList(@PageableDefault(value = 2) Pageable pageable) {
        return new ModelAndView("/showtime/view-showtime",
                "showtimeList", showTimeService.findAllShowtime(pageable));
    }

    @GetMapping("/add")
    public ModelAndView showAddNewShowtime() {
        return new ModelAndView("/showtime/add", "showtime", new ShowTime());
    }
    @PostMapping("/confirm-add")
    public ModelAndView addNewShowtime(@ModelAttribute ShowTime showTime) {
        showTimeService.saveNewShowtime(showTime);
        return new ModelAndView("redirect:/dashboard/showtime");
    }
    @GetMapping("/update/{id}")
    public ModelAndView showUpdateShowtime(@PathVariable Long id) {
        return new ModelAndView("/showtime/update", "showtime", showTimeService.findShowtimeById(id));
    }
}
