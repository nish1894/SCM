package com.scm.controllers;


import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.services.UserService;


@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    //user dashboard page

    @RequestMapping(value = "/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }
    
    //user prfoile page 
    @RequestMapping(value = "/profile")
    public String userProfile(Model model, Authentication authentication) {
        // String username =  Helper.getEmailOfLoggedInUser(authentication);


        // logger.info("User Profile in: {}", username);

        // //fetch data from database 

        // User user = userService.getUserByEmail(username);

        // // System.out.println(user.getName());
        // // System.out.println(user.getEmail());

        // model.addAttribute("loggedInUser", user);

        return "user/profile";
    }
    


    // user add contacts page

    //user view contacts

    // user edit contact



}
