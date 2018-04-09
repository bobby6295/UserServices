package com.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    demoComponent demoComponent;

    ArrayList<String> l;

    @RequestMapping(value = "/demo1", method = RequestMethod.GET)
    public List<String> demostring() {
        l = new ArrayList<>();
        l.add("bobby");
        l.add("sharma");
        return l;
    }


    @RequestMapping(value = "/component", method = RequestMethod.GET)
    public String componenDemo() {

        return demoComponent.componentDemo();

    }


}
