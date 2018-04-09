package com.demo.utils;


import com.demo.domain.Session;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisService {



    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @Resource(name = "redisUserTemplate")
    private ValueOperations<String, String> valOps;

    public Map<String, Object> saveDataInRedis(String id, Object obj) {
        Map<String, Object> result = new HashMap<>();
        String jsonObj = "";
        try {
            jsonObj = objectMapper.writeValueAsString(obj);

        } catch (JsonProcessingException jpe) {

        }
        valOps.set(id, jsonObj);
        result.put("isSuccess", true);
        result.put("massage", "Data saved succesfully in redis");
          return result;
    }

    public Map<String, Object> getDataFromRedis(String id) {
        Map<String, Object> result = new HashMap<>();
        String jsonObj = valOps.get(id);
        System.out.println("jsonObj :: " + jsonObj);
        Session obj = null;
        try {
            obj = (Session) objectMapper.readValue(jsonObj, Session.class);
        } catch (Exception e) {
            }
        if (result != null) {
            result.put("isSuccess", true);
            result.put("data", obj);
        }

        return result;

    }

    public Boolean tokenExpire(String id, Long seconds) {
        Boolean isExpire = valOps.getOperations().expire(id, seconds, TimeUnit.MILLISECONDS);
        return isExpire;

    }
}
