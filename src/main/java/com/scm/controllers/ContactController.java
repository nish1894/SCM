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
import org.springframework.data.domain.Page;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.forms.ContactSearchForm;
import com.scm.helpers.AppConstants;
import com.scm.helpers.Helper;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.ContactService;
import com.scm.services.ImageService;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



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
    
     // view contacts

    @RequestMapping
    public String viewContacts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE + "") int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction, Model model,
            Authentication authentication) {

        // load all the user contacts
        String username = Helper.getEmailOfLoggedInUser(authentication);

        User user = userService.getUserByEmail(username);

        Page<Contact> pageContact = contactService.getByUser(user, page, size, sortBy, direction);

        model.addAttribute("pageContact", pageContact);
        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);

        model.addAttribute("contactSearchForm", new ContactSearchForm());

        return "user/contacts";
    }

        // search handler
 
     @RequestMapping("/search")
     public String searchHandler(
 
             @ModelAttribute ContactSearchForm contactSearchForm,
             @RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE + "") int size,
             @RequestParam(value = "page", defaultValue = "0") int page,
             @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
             @RequestParam(value = "direction", defaultValue = "asc") String direction,
             Model model,
             Authentication authentication) {


        //Validate if the field is empty
        if (contactSearchForm.getField() == null || contactSearchForm.getField().trim().isEmpty()) {
            return "user/contacts"; // Redirect or show the contacts page with an error message
            }
 
        logger.info("field {} keyword {}", contactSearchForm.getField(), contactSearchForm.getValue());
 
         var user = userService.getUserByEmail(Helper.getEmailOfLoggedInUser(authentication));
 
         Page<Contact> pageContact = null;
         if (contactSearchForm.getField().equalsIgnoreCase("name")) {
             pageContact = contactService.searchByName(contactSearchForm.getValue(), size, page, sortBy, direction,
                     user);
         } else if (contactSearchForm.getField().equalsIgnoreCase("email")) {
             pageContact = contactService.searchByEmail(contactSearchForm.getValue(), size, page, sortBy, direction,
                     user);
         } else if (contactSearchForm.getField().equalsIgnoreCase("phone")) {
             pageContact = contactService.searchByPhoneNumber(contactSearchForm.getValue(), size, page, sortBy,
                     direction, user);
         }
 
         logger.info("pageContact {}", pageContact);
 
         model.addAttribute("contactSearchForm", contactSearchForm);
 
         model.addAttribute("pageContact", pageContact);
 
         model.addAttribute("pageSize", AppConstants.PAGE_SIZE);
 
         return "user/search";
     }
  
}
