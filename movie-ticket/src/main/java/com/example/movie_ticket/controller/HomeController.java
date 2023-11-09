package com.example.movie_ticket.controller;

import com.example.movie_ticket.dto.SignUpDto;
import com.example.movie_ticket.model.Account;
import com.example.movie_ticket.model.Customer;
import com.example.movie_ticket.model.Role;
import com.example.movie_ticket.repository.IAccountRepo;
import com.example.movie_ticket.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.security.auth.login.LoginContext;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Autowired
    private IRoleService roleService;
    @GetMapping
    public String showHome(Model model, @PageableDefault(value = 12) Pageable pageable) {
        model.addAttribute("movies",movieService.findMovieOrderByDate(pageable));
        model.addAttribute("categories",categoryService.getAllCategory());
        return "index";
    }
    @GetMapping("/check-out")
    public ModelAndView showCheckOut(){
        return new ModelAndView("checkout");
    }

    @GetMapping("/login")
    public ModelAndView showLogin() {
        return new ModelAndView("login");
    }

    @GetMapping("/signup")
    public ModelAndView showSignUp(){
        return new ModelAndView("signup","signup",new SignUpDto());
    }
    @PostMapping("/signup")
    public ModelAndView showSignUp(@Valid @ModelAttribute(name = "signup") SignUpDto signUpDto,
                                   BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (accountService.findByUsername(signUpDto.getUsername()) != null){
            bindingResult.rejectValue("username",null,"Username đã tồn tại trong hệ thống");
        }
        if (customerService.findByEmail(signUpDto.getEmail()) != null){
            bindingResult.rejectValue("email",null,"Email đã được đăng kí tài khoản khác trong hệ thống");
        }
        if (customerService.findByPhone(signUpDto.getPhoneNumber()) != null){
            bindingResult.rejectValue("phoneNumber",null,"Số điện thoại đã được đăng kí tài khoản khác trong hệ thống");
        }
        signUpDto.validate(signUpDto,bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("signup", "signUpDto", signUpDto);
        } else {
            List<Role> roles = new ArrayList<>();
            roles.add(roleService.findByNameRole("ROLE_USER"));
            Account account = new Account();
            BeanUtils.copyProperties(signUpDto,account);
            account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));
            account.setRoles(roles);
            accountService.signUpAccount(account);
            Customer customer = new Customer();
            BeanUtils.copyProperties(signUpDto,customer);
            customer.setAccount(account);
            customerService.saveCustomer(customer);
            redirectAttributes.addFlashAttribute("signupSuccess","Đăng kí thành công, vui lòng đăng nhập để tiếp tục");
            return new ModelAndView("redirect:/login");
        }
    }

    @GetMapping("/dashboard-admin-order")
    public ModelAndView showDashboardAdminOrder(){
        return new ModelAndView("dashboard-admin-table");
    }
}
