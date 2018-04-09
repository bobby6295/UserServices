package com.demo.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.DTO.LoginDTO;
import com.demo.service.UserService;
import io.jsonwebtoken.Claims;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {



    @Autowired
    UserService userService;
    public JwtAuthenticationFilter() {



    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {

              com.demo.domain.User cred = new ObjectMapper()
                    .readValue(request.getInputStream() ,com.demo.domain.User.class);

            return this.getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(cred.getEmail(), cred.getPassword(), new ArrayList<>()));


        } catch (IOException e) {

            throw new RuntimeException(e);

        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                                      Authentication authResult) throws IOException, ServletException {


        String token = Jwts.builder()
                .setSubject(((User) authResult.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + SecurityContraint.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityContraint.SECRET.getBytes())
                .compact();
        response.addHeader(SecurityContraint.HEADER_STRING, SecurityContraint.TOKEN_PREFIX + token);

System.err.println("++++++++++++++++++++++++++++++++++++");


    }






}
