package com.example.CRUD.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String home(Authentication authentication) {
        if (authentication != null) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (authority.getAuthority().equals("ROLE_ADMIN")) {
                    return "redirect:/admin/dashboard";
                }
                if (authority.getAuthority().equals("ROLE_TECH")) {
                    return "redirect:/tech/dashboard";
                }
            }
        }
        return "redirect:/user/dashboard";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin_dashboard";
    }

    @GetMapping("/tech/dashboard")
    public String techDashboard() {
        return "tech_dashboard";
    }

    @GetMapping("/user/dashboard")
    public String userDashboard() {
        return "user_dashboard";
    }
}
