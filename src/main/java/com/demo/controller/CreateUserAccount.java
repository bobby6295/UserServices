package com.demo.controller;


import com.demo.DTO.*;

import com.demo.emailConfirmation.OnRegistrationCompleteEvent;
import com.demo.mapping.URLMapping;
import com.demo.repository.UserRepository;
import com.demo.utils.GenericUtils;
import com.demo.utils.ResponseHandler;
import com.demo.domain.User;
import com.demo.domain.VerificationToken;
import com.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;


@RestController
public class CreateUserAccount {


    @Autowired
    UserService userService;


    @Autowired
    UserRepository userRepository;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;


    @Autowired
    MessageSource messages;

    @Autowired
    private IUserService iUserService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RestTemplate restTemplate;



    @Autowired
  ProductClient productClient;

    @RequestMapping(value = URLMapping.REGISTRATION, method = RequestMethod.POST )
    public ResponseEntity<Object> resgisterUserAccount(@Valid @RequestBody UserDto userDto, WebRequest request) {

        Map<String, Object> result = null;
        try {

            result = userService.registerNewUserAccount(userDto);
            if (result.get("isSuccess").equals(true)) {


                try {
                    String appUrl = request.getContextPath();

                    applicationEventPublisher.publishEvent(
                            new OnRegistrationCompleteEvent((User) result.get("user"), appUrl, request.getLocale()));
                } catch (Exception em) {


                    return ResponseHandler.generateResponse(HttpStatus.CONTINUE, false, "verification fail", result);


                }
                return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);
            } else

                return ResponseHandler.generateResponse(HttpStatus.OK, false, result.get("message").toString(), result);
        } catch (Exception e) {

            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);

        }

    }


    @RequestMapping(value = "/assign/role", method = RequestMethod.POST)
    public ResponseEntity<Object> assignRole(@RequestBody AssignRole assignDto) {

        Map<String, Object> result = null;
        try {

            result = userService.assignRole(assignDto);
            if (result.get("isSuccess").equals(true)) {
                return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);
            } else

                return ResponseHandler.generateResponse(HttpStatus.OK, false, result.get("message").toString(), result);
        } catch (Exception e) {


            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);

        }
    }


    @RequestMapping(value = URLMapping.VERIFICATION, method = RequestMethod.GET)
    public String confirmRegistration
            (WebRequest request, Model model, @RequestParam("token") String token) {


        Locale locale = request.getLocale();


        VerificationToken verificationToken = iUserService.getVerificationToken(token);
        if (verificationToken == null) {

            return "Token NULL" + locale.getLanguage();
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();


        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = messages.getMessage("auth.message.expired", null, locale);
            model.addAttribute("message", messageValue);
            return "Bad URL " + locale.getLanguage();
        }

        user.setEnabled(true);
        iUserService.saveRegisteredUser(user);
        return "Enabled" + request.getLocale().getLanguage();
    }


    @RequestMapping(value = URLMapping.LOGIN, method = RequestMethod.POST)
    ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request,HttpServletResponse response) {
        System.err.println(loginDTO.getEmail() + ":      " + loginDTO.getPassword());
        if (loginDTO.getEmail().equals("") || loginDTO.getEmail() == null) {

            return ResponseHandler.invalidResponse(HttpStatus.BAD_REQUEST, false, "Please enter valid email");
        } else if (loginDTO.getPassword().equals("") || loginDTO.getPassword() == null) {

            return ResponseHandler.invalidResponse(HttpStatus.BAD_REQUEST, false, "Please enter valid password");
        } else if (loginDTO.getEmail().contains("admin")) {

            return ResponseHandler.invalidResponse(HttpStatus.NOT_ACCEPTABLE, false, "Login not acceptable");
        } else {
           Map<String,Object> result= userService.login(loginDTO, request,response);
           return new ResponseEntity<Object>(result,HttpStatus.OK);
        }
    }


    @RequestMapping(value = URLMapping.FORGOTPASSWORD, method = RequestMethod.GET)
    ResponseEntity<Object> forgetPassword(@RequestParam String email) {
        if (email.equals("") || email == null) {

            return ResponseHandler.invalidResponse(HttpStatus.BAD_REQUEST, false, "Please enter valid email");
        } else {

            return userService.forgetPassword(email);
        }
    }


    @RequestMapping(value = URLMapping.FORGOTPASSWORDMOBILE, method = RequestMethod.GET)
    ResponseEntity<Object> forgetPasswordMobile(@RequestParam String mobileNo) {
        if (mobileNo.equals("") || mobileNo == null) {

            return ResponseHandler.invalidResponse(HttpStatus.BAD_REQUEST, false, "Please enter valid email");
        } else {

            return userService.forgetPasswordMobile(mobileNo);
        }
    }


    @RequestMapping(value = URLMapping.RESETPASSWORD, method = RequestMethod.POST)
    ResponseEntity<Object> resetPassword(@Valid @RequestBody ResetDto resetDto) {

        if (!resetDto.getPassword().equals(resetDto.getConfirmPassword())) {

            return ResponseHandler.invalidResponse(HttpStatus.BAD_REQUEST, false, "password not Match");

        } else {
            resetDto.setPassword(bCryptPasswordEncoder.encode(resetDto.getPassword()));

            return userService.resetPassword(resetDto);
        }

    }


    @GetMapping(URLMapping.Get_User)
    public List<User> getAll(HttpServletRequest request,HttpServletResponse response) {
        return userRepository.findAll();

    }






    @RequestMapping(value="/products",method = RequestMethod.GET)
    public List<Object> greeting() {
        return productClient.getStores();
    }



   @RequestMapping(value = URLMapping.LOGOUT,method = RequestMethod.GET)
    public ResponseEntity<Object> logout(HttpServletResponse response,HttpServletRequest request)
   {

String email=GenericUtils.getLoggedInUser();
Map<String,Object> result=userService.logout(email,response);
if (result.get("isSuccess").equals(true))
{
    return ResponseHandler.generateResponse(HttpStatus.OK,true,result.get("logout").toString(),result);
}
       return ResponseHandler.generateResponse(HttpStatus.BAD_GATEWAY,true,result.get("logout").toString(),result);
   }




}















