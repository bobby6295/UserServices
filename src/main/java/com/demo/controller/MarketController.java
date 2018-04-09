package com.demo.controller;

import java.util.List;
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
import com.demo.repository.Marketrepository;
import com.demo.utils.ResponseHandler;
import com.demo.domain.Market;
import com.demo.service.MarketService;

@RestController
public class MarketController {


    @Autowired
    MarketService marketservice;


    @Autowired
    Marketrepository marketrepository;



    @RequestMapping(value = URLMapping.SAVE_MARKET, method = RequestMethod.POST)
    ResponseEntity<Object> savemarket(@RequestBody Market market) {

        Map<String, Object> result = null;

        try {

            result = marketservice.saveMarket(market);
            if (result.get("issuccess").equals(true)) {

                return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);

            } else {


                return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);

            }

        } catch (NullPointerException e) {

            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, result.get("message").toString(), result);

        }
        catch (Exception e)
        {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, result.get("message").toString(), result);



        }


    }


    @RequestMapping(value = URLMapping.GET_MARKET, method = RequestMethod.GET)
    ResponseEntity<Object> getmarket(@RequestParam String marketId) {
        Map<String, Object> result = null;


        try {
            result = marketservice.getmarket(marketId);

            if (result.get("isSuccess").equals(true)) {

                return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);

            } else {
                return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, result.get("message").toString(), result);

            }


        } catch (Exception e) {

            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);

        }


    }


    @RequestMapping(value = URLMapping.GET_MARKETS, method = RequestMethod.GET)
    ResponseEntity<Object> getMarkets() {
        List<Market> response = null;
        try {
            response = marketrepository.findAll();

            if (response != null)
                return ResponseHandler.generateResponse(HttpStatus.OK, true, "Markets fetched successfully", response);
            else
                return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, "There is no records.", response);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), response);
        }
    }


    @RequestMapping(value = URLMapping.UPDATE_MARKET, method = RequestMethod.PUT)
    ResponseEntity<Object> updateMarket(@RequestParam String marketId, @RequestBody Market market) {
        Map<String, Object> result = null;
        try {

            result = marketservice.updateMarket(marketId, market);
            if (result.get("isSuccess").equals(true)) {
                return ResponseHandler.generateResponse(HttpStatus.OK, true, "Success", result);
            } else
                return ResponseHandler.generateResponse(HttpStatus.OK, false, "not Sucess", result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);
        }
    }


}
