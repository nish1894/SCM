package com.scm.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.entities.User;
import com.scm.helpers.ResourceNotFoundException;
import com.scm.services.UserService;
import com.scm.repositories.UserRepo;

//methods of UserService interface are implemented here
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo; // save data in database

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class); // log data in database

    @Override
    public User saveUser(User user) {
        // user id : have to genrate
        String userId =UUID.randomUUID().toString();
        user.setUserId(userId);

        return userRepo.save(user);

    }

    @Override
    public Optional<User> getUserbyId(String Id) {
        return userRepo.findById(Id);

    }

    @Override
    public Optional<User> updateUser(User user) {
        User user2 = userRepo.findById(user.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setProfilePicLink(user.getProfilePicLink());
        user2.setAbout(user.getAbout());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneVerified(user.isPhoneVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderUserid(user.getProviderUserid());

        // save the user in database

        User save = userRepo.save(user2);
        return Optional.ofNullable(save);

    }

    @Override
    public void deleteUser(String Id) {
        User user2 = userRepo.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepo.delete(user2);

    }

    @Override
    public boolean isUserExist(String Id) {
        User user2 = userRepo.findById(Id).orElse(null);
        return user2 != null ?true:false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User user2 = userRepo.findByEmail(email).orElse(null);
            return user2 != null ? true : false;
    }

    @Override
    public boolean isUserExistByPhone(String phone) {
            User user2 = userRepo.findByPhoneNumber(phone).orElse(null);
            return user2 != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

}
