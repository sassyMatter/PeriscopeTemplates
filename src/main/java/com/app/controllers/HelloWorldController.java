package com.app.controllers;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
//@CrossOrigin(origins = "*")
public class HelloWorldController{

    @GetMapping
    public String helloWorld(){
        return "Hello from server";
    }

}
