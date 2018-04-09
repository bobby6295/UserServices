package com.demo.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.demo.mapping.URLMapping;
import com.demo.utils.ResponseHandler;
import com.demo.domain.ExchangeCountry;
import com.demo.service.ExchangeCountryService;
import com.demo.service.MarketService;


@RestController
public class ExchangeCountryController {


    @Autowired
    ExchangeCountryService exchangeService;


    @RequestMapping(value = URLMapping.ADD_EXCHANGE, method = RequestMethod.POST)
    ResponseEntity<Object> saveExchange(@RequestBody ExchangeCountry data) {


        Map<String, Object> result = null;
        try {
            result = exchangeService.saveExchange(data);
            if (result.get("isSuccess").equals(true)) {

                return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);

            }

            return ResponseHandler.generateResponse(HttpStatus.OK, false, result.get("message").toString(), result);

        } catch (NullPointerException e) {


            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);

        } catch (Exception e) {

            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);


        }

    }


    @RequestMapping(value = URLMapping.GET_EXCHANGE, method = RequestMethod.GET)
    ResponseEntity<Object> getExchange(@RequestParam String exchangeId) {
        Map<String, Object> result = null;
        try {
            result = exchangeService.getExchange(exchangeId);
            if (result.get("isSuccess").equals(true)) {
                return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);
            } else
                return ResponseHandler.generateResponse(HttpStatus.OK, false, result.get("message").toString(), result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);
        }
    }


    @RequestMapping(value = URLMapping.UPDATE_EXCHANGE, method = RequestMethod.PUT)
    ResponseEntity<Object> updateExchange(@RequestParam String exchangeId, @RequestBody ExchangeCountry exchangeCountry) {
        Map<String, Object> result = null;
        try {
            result = exchangeService.updateExchange(exchangeId, exchangeCountry);
            if (result.get("isSuccess").equals(true)) {
                return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);
            } else
                return ResponseHandler.generateResponse(HttpStatus.OK, false, result.get("message").toString(), result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);
        }
    }


}