package com.demo.controller;


import com.demo.utils.ResponseHandler;
import com.demo.domain.Role;
import com.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RoleController {

    @Autowired
    RoleService roleService;


    @RequestMapping(value = "/add/role", method = RequestMethod.POST)
    public ResponseEntity<Object> saveRole(@RequestBody Role role) {

        Map<String, Object> result = null;
        try {

            result = roleService.saveRole(role);
            if (result.get("isSuccess").equals(true)) {
                return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);
            } else

                return ResponseHandler.generateResponse(HttpStatus.OK, false, result.get("message").toString(), result);
        } catch (Exception e) {


            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);

        }
    }


}
