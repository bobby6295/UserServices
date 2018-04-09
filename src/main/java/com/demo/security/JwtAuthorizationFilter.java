package com.demo.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {


    Logger logger = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {


        String header = request.getHeader("Authorization");
        System.err.println(request.getAuthType());
        System.err.println(request.getHeader(SecurityContraint.HEADER_STRING));

        System.err.println(header);
        if (header == null || !header.startsWith(SecurityContraint.TOKEN_PREFIX)) {
            System.err.println("------------------------");
            chain.doFilter(request, response);
            return;
        }


        System.err.println("else");

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        try {


            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = Jwts.builder().
                    setSubject(authentication.getName())
                    .setExpiration(new Date(System.currentTimeMillis() + SecurityContraint.EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS512, SecurityContraint.SECRET.getBytes())
                    .compact();
            response.addHeader(SecurityContraint.HEADER_STRING, SecurityContraint.TOKEN_PREFIX + token);

            chain.doFilter(request, response);
        } catch (NullPointerException e) {


            logger.info(e.getMessage());
        } catch (Exception e) {
            logger.info(e.getMessage());

        }


    }


    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityContraint.HEADER_STRING);

        if (token != null) {
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(SecurityContraint.SECRET.getBytes())
                    .parseClaimsJws(token.replace(SecurityContraint.TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();
            Long expirationTime = Jwts.parser()
                    .setSigningKey(SecurityContraint.SECRET.getBytes())
                    .parseClaimsJws(token.replace(SecurityContraint.TOKEN_PREFIX, ""))
                    .getBody()
                    .getExpiration().getTime();

            Long currenttime = System.currentTimeMillis();

            System.err.println("Current TimeMiless " + currenttime);
            if (expirationTime - currenttime >= 0) {

                System.err.println(expirationTime - currenttime >= 0);
                if (user != null) {

                    return new UsernamePasswordAuthenticationToken(user, 0, new ArrayList<>());

                }
                return null;

            } else {

                System.err.println("Session Expire");
                return null;

            }


        }
        return null;
    }


}
