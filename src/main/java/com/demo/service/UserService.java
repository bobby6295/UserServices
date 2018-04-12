package com.demo.service;

import com.demo.DTO.*;
import com.demo.configuration.Envconfig;
import com.demo.constant.Message;
import com.demo.domain.Session;
import com.demo.repository.RoleRepository;
import com.demo.repository.UserRepository;

import com.demo.repository.VerificationTokenRepository;
import com.demo.security.SecurityContraint;
import com.demo.utils.RedisService;
import com.demo.utils.ResponseHandler;
import com.demo.domain.Role;
import com.demo.domain.User;
import com.demo.domain.VerificationToken;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.*;

@Service
@Transactional
public class UserService implements IUserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    VerificationTokenRepository tokenRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MessageService messageService;


    @Autowired
    JwtService jwtService;

    @Autowired
    RedisService redisService;

    @Autowired
    JavaMailSender javaMailSender;


    @Autowired
    Envconfig envconfig;

    @Autowired
    CustomAuthentication customAuthentication;


    Logger LOGGER = LoggerFactory.getLogger(UserService.class);


    public Map<String, Object> registerNewUserAccount(UserDto accountDto) {

        Map<String, Object> result = new HashMap<>();
        Boolean isSuccess = false;
        User user = new User();
        try {
            if (userRepository.findByEmail(accountDto.getEmail()) != null) {

                result.put("isSuccess", isSuccess);
                result.put("message", Message.ALREADY_EXIST);
                result.put("user", user);
                return result;
            } else {

                user.setFirstName(accountDto.getFirstName());
                user.setLastName(accountDto.getLastName());
                user.setEmail(accountDto.getEmail());
                user.setMobileNo(accountDto.getMobileNO());
                user.setPassword(bCryptPasswordEncoder.encode(accountDto.getPassword()));
                userRepository.save(user);
                result.put("isSuccess", true);
                result.put("message", Message.SUCCESS);

                result.put("user", user);
                LOGGER.info("User Register SuccessFully");
                return result;
            }
        } catch (NullPointerException e) {
            // TODO: handle exception
            LOGGER.warn(e.getMessage());
            result.put("message", Message.ERROR);
            result.put("isSuccess", isSuccess)
            ;
            result.put("user", user);
            LOGGER.info("Null Pointer Exception");
        } catch (Exception e) {
            // TODO: handle exception
            LOGGER.warn(e.getMessage());
            result.put("message", Message.ERROR);
            result.put("isSuccess", isSuccess);
            LOGGER.info("Exception");

        }


        return result;

    }


    public Map<String, Object> assignRole(AssignRole assignRole) {
        Map<String, Object> result = new HashMap<>();
        boolean isSuccess = false;

        Long roleId = assignRole.getRoleId();
        Long[] userIdies = assignRole.getUserId();

        List<User> users = new ArrayList<>();


        if (roleId != null && userIdies != null) {

            Role role = roleRepository.findOne(assignRole.getRoleId());

            try {
                for (Long Userid : userIdies) {
                    User user = userRepository.findOne(Userid);
                    if (user == null) {


                        result.put("error", Userid);
                        result.put("message", messageService.getMessage(Message.ERROR));
                        result.put("isSuccess", isSuccess);
                        return result;
                    }
                    users.add(user);
                }

                role.setUsers(users);
                roleRepository.save(role);

                isSuccess = true;
                result.put("isSuccess", isSuccess);
            } catch (Exception e) {
                LOGGER.warn(e.getMessage());
                result.put("message", messageService.getMessage(Message.INTERNAL_SERVER_ERROR));
            }
        } else {
            LOGGER.info("Invalid data");
            result.put("message", messageService.getMessage(Message.INVALID_INPUTS));
        }
        result.put("isSuccess", isSuccess);
        return result;
    }


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    public ResponseEntity<Object> login(LoginDTO dto, HttpServletRequest request) {

        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findByEmail(dto.getEmail());

        LOGGER.info("++++++++++++++++++++++++++++++++++++++++++++");
        if (user != null) {
            Boolean CorrectCredential = jwtService.authenticate(dto, request);

            if (CorrectCredential) {
                LOGGER.info("____________________-------------------------------------------------sdfffff");
                String token = jwtService.getToken(user);
                Session session = new Session();
                session.setAuthenticationToken(token);
                session.setLoginTime(System.currentTimeMillis());
                session.setExpirationTime(System.currentTimeMillis() + SecurityContraint.EXPIRATION_TIME);
                redisService.saveDataInRedis(user.getEmail(), session);
                result.put("loginTime", new Date());
                result.put("email", user.getEmail());
                User user1 = userRepository.findByEmail(user.getEmail());
                result.put("fullName", user.getFirstName() + " " + user.getLastName());

                return ResponseHandler.generateLoginResponse(HttpStatus.OK, true, "User logged in successfully", result,
                        token);
            } else {

                return ResponseHandler.invalidResponse(HttpStatus.OK, false, "wrong username and password");
            }

        } else {

            return ResponseHandler.invalidResponse(HttpStatus.OK, false, "wrong email entered");
        }
    }


    public Map<String, Object> login(LoginDTO dto, HttpServletRequest request, HttpServletResponse response) {


        System.err.println("++++++++++++++++++++++++++++++");
        Map<String, Object> result = new HashMap<>();
        Boolean correctCredential = customAuthentication.authenticate(dto, request, response);
        System.err.println("boolean");

        if (correctCredential) {
            User user = userRepository.findByEmail(dto.getEmail());
            System.err.println("+++++++++++");
            String token = customAuthentication.authenticationToken(user);
            System.err.println(token);
            response.addHeader("Authorization", SecurityContraint.TOKEN_PREFIX + token);
            result.put("Token", token);
            result.put("user", user);

            System.err.println("Map");

            return result;

        }

        return result;


    }


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    public ResponseEntity<Object> forgetPassword(String email) {

        try {
            User user = userRepository.findByEmail(email);
            if (user != null) {
                String token = UUID.randomUUID().toString();
                createVerificationToken(user, token);
                SimpleMailMessage mail = new SimpleMailMessage();
                mail.setSubject("Resset Password");
                mail.setTo(user.getEmail());
                mail.setText("don Not Reply Click on th Link to Reset Your Password\n" +
                        envconfig.getAppUrl() + "/api-accounts/v1" + "reset-password?" + token);
                javaMailSender.send(mail);
                return ResponseHandler.generateResponse(HttpStatus.OK, true, "Visit Email to reset password", token);
            }

        } catch (NullPointerException e) {

            return ResponseHandler.invalidResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());

        } catch (Exception e) {

            return ResponseHandler.invalidResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());

        }
        return ResponseHandler.invalidResponse(HttpStatus.BAD_REQUEST, false, "User not Exists");
    }


    public ResponseEntity<Object> forgetPasswordMobile(String mobileNo) {

        try {
            User user = userRepository.findByMobileNo(mobileNo);
            if (user != null) {
                String token = UUID.randomUUID().toString();
                createVerificationToken(user, token);
                SimpleMailMessage mail = new SimpleMailMessage();
                mail.setSubject("Resset Password");
                mail.setTo(user.getEmail());
                mail.setText("don Not Reply Click on th Link to Reset Your Password\n" +
                        envconfig.getAppUrl() + "/api-accounts/v1" + "reset-password?" + token);
                javaMailSender.send(mail);
                return ResponseHandler.generateResponse(HttpStatus.OK, true, "Visit Email to reset password", token);
            }

        } catch (NullPointerException e) {

            return ResponseHandler.invalidResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());

        } catch (Exception e) {

            return ResponseHandler.invalidResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());

        }
        return ResponseHandler.invalidResponse(HttpStatus.BAD_REQUEST, false, "User not Exists");
    }


    public ResponseEntity<Object> resetPassword(ResetDto resetDto) {


        User user1 = null;
        try {
            VerificationToken verificationToken = getVerificationToken(resetDto.getAuth());
            if (verificationToken != null) {
                User user = getUser(resetDto.getAuth());
                Calendar cal = Calendar.getInstance();
                if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) >= 0) {
                    user.setPassword(resetDto.getPassword());
                    user1 = user;
                    userRepository.save(user);
                } else {
                    Date date = Date.from(Instant.now());
                    tokenRepository.deleteAllExpiredSince(date);
                    return ResponseHandler.invalidResponse(HttpStatus.BAD_REQUEST, false, "Time Expired");

                }
            } else {

                return ResponseHandler.invalidResponse(HttpStatus.BAD_REQUEST, false, "Enter Valid Token");

            }

        } catch (NullPointerException e)

        {
            return ResponseHandler.invalidResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());


        } catch (Exception e) {

            return ResponseHandler.invalidResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());

        }
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Password Reset Sucessfuly", user1);

    }





    public Map<String,Object> logout(String email,HttpServletResponse response)
    {

        User user1=userRepository.findByEmail(email);
        Map<String,Object> result=new HashMap<>();

        System.err.println(response.getHeader(SecurityContraint.HEADER_STRING));
        if (response.getHeader(SecurityContraint.HEADER_STRING).equals("")) {

            result.put("logout","User Logout Successfully");
            result.put("data",user1);
            result.put("isSuccess",true);
            return result;
        }
        result.put("logout","User Logout failed");
        result.put("data",null);
        result.put("isSuccess",false);
        return result;

    }


    @Override
    public User getUser(String verificationToken) {
        User user = tokenRepository.findByToken(verificationToken).getUser();
        return user;
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    @Override
    public void saveRegisteredUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(user, token);
        tokenRepository.save(myToken);
    }


}
