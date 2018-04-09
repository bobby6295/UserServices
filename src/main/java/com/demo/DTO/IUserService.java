package com.demo.DTO;


import com.demo.domain.User;
import com.demo.domain.VerificationToken;

import java.util.Map;

public interface IUserService {


    Map<String,Object> registerNewUserAccount(UserDto accountDto)
            ;

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);





}
