package com.example.movie_ticket.controller.dashboard;

import com.example.movie_ticket.model.*;
import com.example.movie_ticket.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private IBookingService bookingService;

    @Autowired
    private IAccountService accountService ;

    @GetMapping
    public ModelAndView showDashboard(Principal principal) {
        return new ModelAndView("dashboard","account",accountService.findByUsername(principal.getName()));
    }
}