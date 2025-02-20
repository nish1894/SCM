package com.scm.services;

import com.scm.entities.User;
import java.util.Optional;
import java.util.List;
import com.scm.entities.User;

public interface UserService {

    User saveUser(User user);
    Optional<User> getUserbyId(String Id);
    User updateUser(User user);
    void deleteUser(String Id);
    boolean isUserExist(String Id);
    boolean isUserExistByEmail(String email);
    boolean isUserExistByPhone(String phone);
    List<User> getAllUsers();


    // add more methods here related to user services[logic]


}
 