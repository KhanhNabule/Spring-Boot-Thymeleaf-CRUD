package com.luv2code.springboot.cruddemo.controller;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/list")
    public String getEmployee(Model model) {
        List<Employee> employeeList = employeeService.findAll();
        model.addAttribute("employees", employeeList);
        return "employees/list-employees";
    }
    @GetMapping("/showFormForAdd")
    public String showFormEmployee(Model model) {
        Employee employees = new Employee();
        model.addAttribute("employees", employees);
        return "employees/employee-form";
    }
    @PostMapping("/save")
    public String addEmployee(@ModelAttribute("employees") Employee theEmployee) {
        employeeService.save(theEmployee);
        return "redirect:/employees/list";
    }
}