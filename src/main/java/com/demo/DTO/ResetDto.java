package com.demo.DTO;


import com.demo.annotation.PasswordMatches;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@PasswordMatches
public class ResetDto {




    @NotNull
    @NotEmpty
    private String password;

    @NotEmpty
    @NotNull
    private String confirmPassword;


    private String auth;

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getPassword() {
        return password;
    }


    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
