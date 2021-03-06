package com.demo.DTO;



import org.springframework.cloud.netflix.feign.FeignClient;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
@FeignClient("CATALOG-SERVICE")
public interface ProductClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/product/products")
    List<Object> getStores();

}
