package com.example.movie_ticket.controller;

import com.example.movie_ticket.dto.SignUpDto;
import com.example.movie_ticket.model.Account;
import com.example.movie_ticket.model.Customer;
import com.example.movie_ticket.repository.IAccountRepo;
import com.example.movie_ticket.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping
public class HomeController {
    @Autowired
    private ISeatService seatService;
    @Autowired
    private IMovieService movieService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private ICustomerService customerService;
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

    @GetMapping("/login")
    public ModelAndView showLogin(){
        return new ModelAndView("login");
    }

    @GetMapping("/signup")
    public ModelAndView showSignUp(){
        return new ModelAndView("signup","signup",new SignUpDto());
    }
    @PostMapping("/signup")
    public ModelAndView showSignUp(@Valid @ModelAttribute("signup") SignUpDto signUpDto,
                                   BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("signup", "signup", signUpDto);
        } else {
            Account account = new Account();
            BeanUtils.copyProperties(signUpDto,account);
            accountService.signUpAccount(account);
            Customer customer = new Customer();
            BeanUtils.copyProperties(signUpDto,customer);
            customer.setAccount(account);
        }
        return new ModelAndView("signup","signup",new SignUpDto());
    }

    @GetMapping("/dashboard-admin-order")
    public ModelAndView showDashboardAdminOrder(){
        return new ModelAndView("dashboard-admin-table");
    }
}
