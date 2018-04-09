package com.demo.service;


import com.demo.constant.Message;
import com.demo.repository.RoleRepository;
import com.demo.domain.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RoleService {





    Logger LOGGER= LoggerFactory.getLogger(RoleService.class);

    @Autowired
    RoleRepository roleRepository;




    public Map<String, Object> saveRole(Role role) {
        Map<String, Object> result = new HashMap<>();
        boolean isSuccess = false;
        Role saverole=new Role();
        try {

            if (roleRepository.findByName(role.getName())!=null)
                {
                    result.put("message", Message.ALREADY_EXIST);
                    result.put("isSuccess",false);
                    LOGGER.info("already exist ");
                    return  result;
                }


                else {

                saverole.setName(role.getName());
                roleRepository.save(saverole);
                result.put("message", Message.SUCCESS);
                result.put("isSuccess", true);
                LOGGER.info("data save suceesfully");

                return  result;
            }
        } catch (NullPointerException e) {
        // TODO: handle exception
        LOGGER.warn(e.getMessage());
        result.put("message", Message.ERROR);
        result.put("isSuccess", isSuccess)
        ;
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
}