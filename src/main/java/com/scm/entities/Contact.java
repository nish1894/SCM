package com.scm.entities;

import java.util.ArrayList;
import java.util.*;

// import java.util.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String pic_link;

    @Column(length = 10000)
    private String description;

    private boolean favorite;

    @ManyToOne
    private User user; 


    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<SocialLink> links = new ArrayList<>();
    // public contact() {
    // }

    // public contact(String name, String email, String phone, String message) {
    // this.name = name;
    // this.email = email;
    // this.phone = phone;
    // this.message = message;
    // }

    // public String getName() {
    // return name;
    // }

    // public String getEmail() {
    // return email;
    // }

    // public String getPhone() {
    // return phone;
    // }

    // public String getMessage() {
    // return message;
    // }

    // public void setName(String name) {
    // this.name = name;
    // }

    // public void setEmail(String email) {
    // this.email = email;
    // }

    // public void setPhone(String phone) {
    // this.phone = phone;
    // }

    // public void setMessage(String message) {
    // this.message = message;
    // }

    // @Override
    // public String toString() {
    // return "contact{" +
    // "name='" + name + '\'' +
    // ", email='" + email + '\'' +
    // ", phone='" + phone + '\'' +
    // ", message='" + message + '\'' +
    // '}';
    // }
}
