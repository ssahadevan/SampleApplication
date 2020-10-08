package com.ss.hazelcast.SampleApplication.payments.controller;

import com.ss.hazelcast.SampleApplication.payments.model.Employee;
import com.ss.hazelcast.SampleApplication.payments.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("employees")
    public String main(Model model) {

        model.addAttribute("employees", employeeService.generateEmployeeList());
        model.addAttribute("bySalary", Comparator.comparing(Employee::getSalary));

        return "employees";
    }
}
