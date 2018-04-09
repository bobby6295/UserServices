package com.demo.DTO;

import com.demo.annotation.ContactNumberConstraint;
import com.demo.annotation.PasswordMatches;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@PasswordMatches
public class UserDto {



    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @NotNull
    @NotEmpty

    private String email;

    @ContactNumberConstraint
    private String mobileNO;


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNO() {
        return mobileNO;
    }

}
