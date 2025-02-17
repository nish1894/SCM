package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// Handles page navigation
public class PageController {

    @RequestMapping("/home")
    // Home page handler
    public String home(Model model) {
        System.out.println("Home page handler");
        model.addAttribute("githubRepo", "https://github.com/nish1894/SCM");
        return "home";
    }

    @RequestMapping("/about")
    // About page handler
    public String aboutpage() {
        System.out.println("About page handler");
        return "about";
    }

    @RequestMapping("/services")
    // Services page handler
    public String servicespage() {
        System.out.println("Services page handler");
        return "services";
    }

    @RequestMapping("/contact")
    // Contact page handler
    public String contactpage() {
        System.out.println("Contact page handler");
        return "contact";
    }

    @RequestMapping("/login")
    // login page handler
    public String loginpage() {
        System.out.println("Login page handler");
        return "login";
    }

    @RequestMapping("/register")
    // Register page handler
    public String registerpage() {
        System.out.println("Register page handler");
        return "register";
    }

}
