package com.example.movie_ticket.controller;

import com.example.movie_ticket.dto.customer.CustomerDto;
import com.example.movie_ticket.model.Customer;
import com.example.movie_ticket.service.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dashboard/customer")
public class DashboardCustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("")
    public String showdashboardCustomer(Model model) {
        List<Customer> customerList = customerService.getAllCustomer();
        model.addAttribute("customerList", customerList);
        return "/customer/dashboard-admin-customer";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return "redirect:/dashboard/customer";
    }

    @GetMapping("/view/{id}")
    public String getCustomerById(@PathVariable Long id,
                                  Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "/customer/view-customer";
    }

    @GetMapping("/edit/{id}")
    public String getCustomerByIdd(@PathVariable Long id,
                                   Model model) {
        CustomerDto customerDto = new CustomerDto();
        Customer customer = customerService.getCustomerById(id);
        BeanUtils.copyProperties(customer, customerDto);
        model.addAttribute("customerDto", customerDto);
        return "/customer/edit-customer";
    }

    @GetMapping("/create")
    public String formCreateCustomer(Model model) {
        CustomerDto customerDto = new CustomerDto();
        model.addAttribute("customerDto", customerDto);
        return "/customer/create-customer";
    }

    @PostMapping("/edit")
    public String editCustomer(@Validated @ModelAttribute CustomerDto customerDto,
                               BindingResult bindingResult,
                               Model model) {
        new CustomerDto().validate(customerDto, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("customerDto", customerDto);
            return "/customer/edit-customer";
        }
        List<Customer> customerList = customerService.getAllCustomer();
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getEmail().equals(customerDto.getEmail())){
                model.addAttribute("customerDto", customerDto);
                model.addAttribute("messageEmail","Email đã tồn tại xin vui lòng xem lại thông tin của bạn");
                return "/customer/edit-customer";
            } else if (customerList.get(i).getPhoneNumber().equals(customerDto.getPhoneNumber())) {
                model.addAttribute("customerDto", customerDto);
                model.addAttribute("messagePhoneNumber"," Số điện thoại đã tồn tại xin vui lòng xem lại thông tin của bạn");
                return "/customer/edit-customer";
            } else if (customerList.get(i).getIdCard().equals(customerDto.getIdCard())) {
                model.addAttribute("customerDto", customerDto);
                model.addAttribute("messageIdCard","Số CCCD/CMND đã tồn tại xin vui lòng xem lại thông tin của bạn");
                return "/customer/edit-customer";
            }
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto,customer);
        customerService.updateCustomer(customer);
        return "redirect:/dashboard/customer";

    }
}
