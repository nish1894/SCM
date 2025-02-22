package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class UserForm {
    @NotBlank(message = "username is required ")
    @Size(message = "Minimum 3 characters", min = 3)
    private String name;
    @NotBlank(message = "email is required")
    @Email(message = "Invalid email Address")
    private String email;
    @Size(message = "Minimum 6 characters", min = 6)
    private String password;
    @Size(message = "Invalid Phone Number", min = 10, max =14)
    private String phoneNumber;
    @NotBlank(message = "About is required")
    private String about;

}
