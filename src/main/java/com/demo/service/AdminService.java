package com.demo.service;


import com.demo.DTO.LoginDTO;
import com.demo.repository.UserRepository;
import com.demo.security.SecurityContraint;
import com.demo.utils.RedisService;
import com.demo.utils.ResponseHandler;
import com.demo.domain.Session;
import com.demo.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminService {


    @Autowired
    private JwtService jwtService;


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisService redisService;

    public ResponseEntity<Object> adminLogin(LoginDTO adminLogin, HttpServletRequest request) {
        Map<String, Object> res = new HashMap<>();
        User securityUser = userRepository.findByEmail(adminLogin.getEmail());

        if (securityUser != null) {
            boolean correctCredentials = jwtService.authenticate(adminLogin, request);

            if (correctCredentials) {
                String token = jwtService.getToken(securityUser);
                Session session = new Session();
                session.setAuthenticationToken(token);
                session.setLoginTime(System.currentTimeMillis());
                session.setExpirationTime(System.currentTimeMillis() + SecurityContraint.EXPIRATION_TIME);
                redisService.saveDataInRedis(securityUser.getEmail(), session);

                res.put("loginTime", new Date());
                res.put("email", securityUser.getEmail());
                User user = userRepository.findByEmail(securityUser.getEmail());
                res.put("fullName", user.getFirstName() + " " + user.getLastName());

                return ResponseHandler.generateLoginResponse(HttpStatus.OK, true, "Admin logged in successfully", res,
                        token);
            } else {

                return ResponseHandler.invalidResponse(HttpStatus.OK, false, "wrong username and password");
            }
        } else {

            return ResponseHandler.invalidResponse(HttpStatus.OK, false, "wrong email entered");
        }
    }


}
