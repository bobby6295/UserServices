package com.demo.controller;


import com.demo.DTO.LoginDTO;
import com.demo.mapping.URLMapping;
import com.demo.utils.ResponseHandler;
import com.demo.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;

@RestController

public class AdminController {


    private static Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @PostMapping(URLMapping.ADMINLOGIN)
    ResponseEntity<Object> adminLogin(@RequestBody LoginDTO adminLogin, HttpServletRequest request) {
        if (adminLogin.getEmail().equals("") || adminLogin.getEmail() == null) {
            LOGGER.info("Please enter valid email");
            return ResponseHandler.invalidResponse(HttpStatus.BAD_REQUEST, false, "Please enter valid email");
        } else if (adminLogin.getPassword().equals("") || adminLogin.getPassword() == null) {
            LOGGER.info("Please enter valid password");
            return ResponseHandler.invalidResponse(HttpStatus.BAD_REQUEST, false, "Please enter valid password");
        } else if (!adminLogin.getEmail().contains("admin")) {
            LOGGER.info("Login not acceptable");
            return ResponseHandler.invalidResponse(HttpStatus.NOT_ACCEPTABLE, false, "Login not acceptable");
        } else {
            return adminService.adminLogin(adminLogin, request);
        }
    }


}
