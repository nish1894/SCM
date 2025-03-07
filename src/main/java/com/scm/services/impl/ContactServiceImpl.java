package com.scm.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.entities.Contact;
import com.scm.services.ContactService;
import com.scm.repositories.*;
import com.scm.helpers.*;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepo contactRepo;

    @Override
    public Contact save(Contact contact) {
        String contactId  = UUID.randomUUID().toString();
        contact.setId(contactId);

        return contactRepo.save(contact) ;
    }

    @Override
    public Contact update(Contact contact) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public List<Contact> getAll() {
        
        return contactRepo.findAll();
    }

    @Override
    public Contact getById(String id) {
        
        return contactRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact not found with given id")); 
    }

    @Override
    public void delete(String id) {
        var contact = contactRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact not found with given id"));

        contactRepo.delete(contact);

        
    }

    @Override
    public List<Contact> search(String name, String email, String phoneNumber) {
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public List<Contact> getByUserId(String userId) {
            
        return contactRepo.findByUserId(userId);

    }

}
