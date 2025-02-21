package com.scm.services;

import com.scm.entities.User;
import java.util.Optional;
import java.util.List;

public interface UserService {

    User saveUser(User user);
    Optional<User> getUserbyId(String Id); //if user doesnt exist no need to use if and all, optional handles that 
    Optional<User> updateUser(User user);
    void deleteUser(String Id);
    boolean isUserExist(String Id);
    boolean isUserExistByEmail(String email);
    boolean isUserExistByPhone(String phone);
    List<User> getAllUsers();


    // add more methods here related to user services[logic], Business logic, core logic 


}
 