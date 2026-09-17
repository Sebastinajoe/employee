package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepository;

@Controller
public class LoginController {

    @Autowired
    EmployeeRepository repo;

    @GetMapping("/signin")
    public String showLoginPage() {
        return "redirect:/login.html";
    }

    @PostMapping("/signin")
    public String Login(@RequestParam String employeename,
            @RequestParam String password) {

        Employee entity = repo.findByEmployeenameAndPassword(employeename, password);

        if (entity == null) {
            return "redirect:/login.html";
        }

        return "redirect:/dashboard.html";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/index.html";
    }
}