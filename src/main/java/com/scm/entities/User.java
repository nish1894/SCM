package com.scm.entities;

import jakarta.persistence.*;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

@Entity(name = "user")
@Table(name = "user") // can change name, default is class name

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class User {

    @Id
    private String userId;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;
    private String password;

    @Column(length = 10000)
    private String about;
    private String profilePic_link;

    private boolean enabled;

    @Builder.Default
    private boolean emailVerified = false;
    @Builder.Default
    private boolean phoneVerified = false;

    // self google Facevook etc
    @Builder.Default
    private Providers provider = Providers.SELF;
    private String providerUserid;

    // add more filds if needed

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Builder.Default
    private List<Contact> contacts = new ArrayList<>();


}
