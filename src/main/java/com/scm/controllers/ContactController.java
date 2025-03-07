package com.scm.controllers;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.helpers.Helper;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.ContactService;
import com.scm.services.ImageService;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    private Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService; 


    @Autowired
    private ImageService imageService; 

    @Autowired
    private UserService userService;




    // add contact page hadler
    @RequestMapping("/add")
    public String addContactView(Model model){

        ContactForm contactForm = new ContactForm();
       
        // contactForm.setFavorite(true);
        model.addAttribute("contactForm",contactForm);


        return "user/add-contact";
    }

    
    @RequestMapping(value = "/add", method=RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result, Authentication authentication,HttpSession session){
        //process form data

        String username =Helper.getEmailOfLoggedInUser(authentication); 

        // validation logic
        
        if(result.hasErrors()){

            result.getAllErrors().forEach(error -> logger.info(error.toString()) );

            return "user/add-contact";
        }

  

        // form -- contact> 
        User user = userService.getUserByEmail(username);

        //process the contact picture 
        String filename = UUID.randomUUID().toString(); 
        // logger.info("file information: {}", contactForm.getContactImage().getOriginalFilename());

        String fileUrl = imageService.uploadImage(contactForm.getContactImage(), filename);

        //form data
        Contact contact = new Contact();

        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setFavorite(contactForm.isFavorite());
        contact.setUser(user);
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setPic_link(fileUrl);
        contact.setCloudinaryImagePublicId(filename);

        contactService.save(contact); 

        System.out.println(contactForm);


        // contact saved message 
        session.setAttribute("message", Message.builder()
                .content("You have successfully added a new contact")
                .type(MessageType.green)
                .build());

    

        return "redirect:/user/contacts/add";

    }
    
  
    

}
