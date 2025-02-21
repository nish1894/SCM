package com.scm.controllers;


import com.scm.forms.UserForm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String registerpage(Model model) {
        UserForm userForm = new UserForm();
        userForm.setName("Nishant");
        userForm.setEmail("nk8@gmail.com");
        userForm.setPhoneNumber("+91-1234567890");
        userForm.setAbout("I am a software developer");
        model.addAttribute("userForm", userForm);
        System.out.println("Register page handler");
        return "register";
    }

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    // Register page handler
    public String processRegister(@ModelAttribute UserForm userForm) {
        System.out.println("Processing Registration form");
        System.out.println(userForm);
        return "redirect:/login";
    }
}
