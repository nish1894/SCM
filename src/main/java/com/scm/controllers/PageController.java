package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Import the Model class
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/home")
    public String home(Model model) { // Add Model as a parameter
        System.out.println("Home page handler");

        // sending data to view 
        model.addAttribute("githubRepo", "https://github.com/nish1894/SCM");
        return "home"; // This should match the Thymeleaf template name (home.html)
    }
}
