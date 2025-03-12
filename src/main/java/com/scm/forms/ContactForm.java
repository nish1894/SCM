package com.scm.forms;

import org.springframework.web.multipart.MultipartFile;

import com.scm.validators.ValidFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ContactForm {

    @NotBlank(message = "name is required ")
    @Size(message = "Minimum 3 characters", min = 3)
    private String name;

    @Email(message = "Invalid email Address")
    private String email;

    @Size(message = "Invalid Phone Number", min = 10, max =14)
    private String phoneNumber;

    private String address;

    private String description;
    
    private boolean favorite;

    private String websiteLink;

    private String linkedInLink;

    // will create an annotation
    //size
    //resolution

    @ValidFile(message = "Invalid File")
    private MultipartFile contactImage; //will be processed so different name 

    private String pic_link;



}
