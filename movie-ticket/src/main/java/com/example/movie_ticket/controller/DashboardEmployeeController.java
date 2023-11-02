package com.example.movie_ticket.controller;

import com.example.movie_ticket.model.Employee;
import com.example.movie_ticket.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/dashboard/employee")
public class DashboardEmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @GetMapping
    public String showDashboardEmployee(@PageableDefault(value = 2) Pageable pageable,
                                        Model model) {
        model.addAttribute("employees",employeeService.findAllEmployee(pageable));
        return "/employee/dashboard-admin-employee";
    }

    @GetMapping("/add")
    public ModelAndView showFormAddEmployee() {
        return new ModelAndView("/employee/create-employee","employee",new Employee());
    }

    @PostMapping("/add")
    public String saveEmployee(@ModelAttribute Employee employee) {
        employeeService.createEmployee(employee);
        return "redirect:/dashboard/employee";
    }


    @GetMapping("/view/{id}")
    public ModelAndView viewEmployee(@PathVariable Long id) {
        Optional<Employee> employeeOptional = employeeService.findEmployeeById(id);
        if (!employeeOptional.isPresent()) {
            return new ModelAndView("/employee/error-404");
        }
        return new ModelAndView("/employee/view-employee","employee",employeeOptional.get());
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showFormEditEmployee(@PathVariable Long id) {
        Optional<Employee> employeeOptional = employeeService.findEmployeeById(id);
        if (!employeeOptional.isPresent()) {
            return new ModelAndView("/employee/error-404");
        }
        return new ModelAndView("/employee/edit-employee","employee",employeeOptional.get());
    }

    @PostMapping("/edit/{id}")
    public String updateEmployee(@ModelAttribute Employee employee) {
        employeeService.updateEmployee(employee);
        return "redirect:/dashboard/employee";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/dashboard/employee";
    }

    @GetMapping("/search")
    public ModelAndView searchEmployee(@PageableDefault(value = 2) Pageable pageable,
                                       @RequestParam String nameEmployee, Model model){
        Page<Employee> employees = employeeService.searchByIdAndName(nameEmployee,pageable);
        model.addAttribute("nameEmployee",nameEmployee);
        return new ModelAndView("/employee/dashboard-admin-employee","employees",employees);
    }
}
