package com.scm.controllers;

import java.security.Principal;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.helpers.Helper;


@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    //user dashboard page

    @RequestMapping(value = "/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }
    
    //user prfoile page 
    @RequestMapping(value = "/profile")
    public String userProfile(Authentication authentication) {
        String username =  Helper.getEmailOfLoggedInUser(authentication);

        logger.info("User Profile in", username);

        //fetch data from database 

        System.out.println("User profile");

        return "user/profile";
    }
    


    // user add contacts page

    //user view contacts

    // user edit contact



}
