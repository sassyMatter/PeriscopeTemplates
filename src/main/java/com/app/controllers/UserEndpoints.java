package com.app.controllers;

import com.app.models.*;
import com.app.services.CustomCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/converter")
@CrossOrigin(origins = "*")
@Slf4j
public class UserEndpoints {

    @Autowired
    CustomCodeService customCodeService;







}
