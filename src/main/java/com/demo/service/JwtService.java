package com.demo.service;

import com.demo.DTO.LoginDTO;
import com.demo.repository.LoginDetailRepository;
import com.demo.repository.UserRepository;
import com.demo.securityConstraint.SecurityConstants;
import com.demo.domain.LoginDetail;
import com.demo.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class JwtService {


    @Autowired
    LoginDetailRepository loginDetailRepository;


    @Autowired
    UserRepository userRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Boolean authenticate(LoginDTO loginDTO, HttpServletRequest request) {

        Boolean flag;
        User user = userRepository.findByEmail(loginDTO.getEmail());
        if (user == null) {

            flag = false;
            return flag;
        }

        if (user != null && bCryptPasswordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {

            Long id = user.getId();
            LoginDetail loginDetail = new LoginDetail();
            Date date = new Date();
            loginDetail.setCity(loginDTO.getCity());
            Long timeStamp = getTimeStamp(date);
            loginDetail.setUserid(id);
            loginDetail.setLoginTime(timeStamp);
            loginDetail.setIpAddress(request.getRemoteAddr());
            loginDetail.setIpAddress(loginDTO.getClientIp());
            loginDetail.setCity(loginDTO.getCity());
            loginDetailRepository.save(loginDetail);

            flag = true;
            return flag;


        } else {


            flag = false;
            return flag;
        }
    }


    public String getToken(User security) {
        String token = Jwts.builder()
                .setSubject(security.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                //.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET.getBytes())
                .compact();
        return token;

    }


    public Long getTimeStamp(Date now) {
        return (now.getTime());
    }


}
