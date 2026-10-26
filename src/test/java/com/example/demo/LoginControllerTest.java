package com.example.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.Controller.LoginController;
import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepository;

@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeRepository repo;

    @Test
    public void testLoginPage() throws Exception {
        mockMvc.perform(get("/signin"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login.html"));
    }

    @Test
    public void testSuccessfulLogin() throws Exception {

        Employee employee = new Employee();
        employee.setEmployeename("testuser");
        employee.setPassword("testpassword");
        employee.setRole("EMPLOYEE");

        when(repo.findByEmployeenameAndPassword(
                "testuser",
                "testpassword"))
                .thenReturn(employee);

        mockMvc.perform(post("/signin")
                .param("employeename", "testuser")
                .param("password", "testpassword"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dashboard.html"));
    }

    @Test
    public void testFailedLogin() throws Exception {

        when(repo.findByEmployeenameAndPassword(
                "wronguser",
                "wrongpassword"))
                .thenReturn(null);

        mockMvc.perform(post("/signin")
                .param("employeename", "wronguser")
                .param("password", "wrongpassword"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login.html"));
    }

    @Test
    public void testLogout() throws Exception {

        mockMvc.perform(get("/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index.html"));
    }
}