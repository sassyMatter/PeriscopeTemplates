package com.app.controllers;

import com.app.models.*;
import com.app.services.CustomCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/converter")
@CrossOrigin(origins = "*")
@Slf4j
public class UserEndpoints {

    @Autowired
    CustomCodeService myService;

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;






}
