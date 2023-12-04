package com.elouga.webapp.controller;

import com.elouga.webapp.model.Employee;
import com.elouga.webapp.service.EmployeeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Data
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<Employee> listEmployee = service.getEmployees();
        model.addAttribute("employees", listEmployee);
        return "home";
    }

    @GetMapping("/createEmployee")
    public String createEmployee(Model model) {
        Employee e = new Employee();
        model.addAttribute("employee", e);
        return "formNewEmployee";
    }

    @GetMapping("/updateEmployee/{id}")
    public String updateEmployee(@PathVariable("id") final int id, Model model) {
        Employee e = service.getEmployee(id);
        model.addAttribute("employee", e);
        return "formUpdateEmployee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public ModelAndView deleteEmployee(@PathVariable final Long id) {
        service.deleteEmployee(id);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/saveEmployee")
    public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
        if(employee.getId() != null) {
            // Employee from update form has the password field not filled,
            // so we fill it with the current password.
            Employee current = service.getEmployee(employee.getId());
            current.setFirstname(employee.getFirstname());
            current.setLastname(employee.getLastname());
            current.setEmail(employee.getEmail());
            current.setPassword(employee.getPassword());
            service.saveEmployee(current);
            return new ModelAndView("redirect:/");
        }else{
            service.saveEmployee(employee);
            return new ModelAndView("redirect:/");
        }
    }
}
