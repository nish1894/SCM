package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Import the Model class
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    @RequestMapping("/home")
    public String home(Model model) { // Add Model as a parameter
        System.out.println("Home page handler");

        // sending data to view
        model.addAttribute("githubRepo", "https://github.com/nish1894/SCM");
        return "home"; // This should match the Thymeleaf template name (home.html)
    }

    @RequestMapping("/about")
    public String aboutpage() {
        System.out.println("About page handler");
        return "about";
    }

    @RequestMapping("/services")
    public String servicespage(Model model) {
        model.addAttribute("islogin", false);
        System.out.println("Services page handler");
        return "services";
    }

}
