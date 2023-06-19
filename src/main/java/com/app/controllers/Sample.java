package com.app.controllers;



import com.app.models.Response;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin(origins="*" , allowedHeaders = "*")
@RequestMapping("/hello" )
@Slf4j
public class Sample {

    @GetMapping
    public Response Sample(){
        log.info("Hit");
        Response response = new Response();
        response.setResponse("Hello, Connected to server");
        return response;
    }





}
