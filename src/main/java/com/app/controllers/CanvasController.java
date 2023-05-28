package com.app.controllers;



import com.app.models.Response;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/canvas")
@CrossOrigin(origins = "*")
@Slf4j
public class CanvasController {

    @PostMapping("/post-canvas-data")
    public Response PostCanvasData(@RequestBody String data){
        log.info("Saving canvas data {} ", data);
        Response response = new Response();
        String res = "Received data :: " + data;
        response.setResponse(res);
        return response;
    }



}
