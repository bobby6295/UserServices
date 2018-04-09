package com.demo.service;

import com.demo.DTO.LoginDTO;
import com.demo.domain.User;
import com.demo.repository.UserRepository;
import com.demo.security.SecurityContraint;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class CustomAuthentication {


    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    public Boolean authenticate(LoginDTO dto, HttpServletRequest request, HttpServletResponse response) {


        User user = userRepository.findByEmail(dto.getEmail());
        Boolean flag = false;

        if (user != null && bCryptPasswordEncoder.matches(dto.getPassword(), user.getPassword())) {
            flag = true;
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
                return flag;
        } else
            return flag;
    }


    public String authenticationToken(User user) {


        String token = Jwts.builder()
                .setSubject(user.getEmail())
                .setExpiration(new Date(System.currentTimeMillis() + SecurityContraint.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityContraint.SECRET.getBytes())
                .compact();

        return token;
    }


}
