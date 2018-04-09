package com.demo.controller;

import com.demo.asssdsax.POllService;
import com.demo.asssdsax.PollDTO;
import com.demo.domain.PollQuestion;
import com.demo.mapping.URLMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PollController {


    @Autowired
    POllService pollService;

    @RequestMapping(value = "/savePoll", method = RequestMethod.POST)
    public ResponseEntity<Object> savePoll(@RequestBody PollDTO pollDTO) {


        System.err.println("++++++++++++++++++++++");
        PollQuestion pollQuestion = pollService.savePoll(pollDTO);

        return new ResponseEntity<Object>(pollQuestion, HttpStatus.OK);


    }


    @RequestMapping(value = "/getpoll", method = RequestMethod.GET)
    public ResponseEntity<Object> getPoll(@RequestParam String poll) {


        System.err.println("++++++++++++++++++++++");
        PollQuestion pollQuestion = pollService.getpoll(poll);

        return new ResponseEntity<Object>(pollQuestion, HttpStatus.OK);


    }


}

