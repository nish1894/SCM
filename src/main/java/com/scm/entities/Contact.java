package com.scm.entities;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private String phoneNumber;
    private String address;
    private String pic_link;

    private String websiteLink;
    private String linkedInLink;
    
    @Column(length = 10000)
    private String description;

    private boolean favorite;

    private String cloudinaryImagePublicId; 

    @ManyToOne
    @JsonIgnore
    private User user; 


    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<SocialLink> links = new ArrayList<>();
 
}
