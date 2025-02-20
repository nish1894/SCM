package com.scm.repositories;
// intrect with data

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

    //extra methods related to db 
    //custom query methods
    //customer finder methods

}
