package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepository;

@Controller
public class RegisterController {

    @Autowired
    EmployeeRepository repo;

    @GetMapping("/signup")
    public String DisplaySignup() {
        return "redirect:/register.html";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute Employee employee) {

        repo.save(employee);

        return "redirect:/index.html";
    }
}