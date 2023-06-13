package com.app.controllers;



import com.app.models.Response;
import com.app.models.canvas.CanvasData;
import com.app.services.CanvasService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/canvas")
@CrossOrigin(origins="*")
@Slf4j
public class CanvasController {

    @Autowired
    CanvasService canvasService;

    @PostMapping("/post-canvas-data")
    public Response PostCanvasData(@RequestBody CanvasData data){
        log.info("Saving canvas data {} ", data);
        canvasService.saveCanvasData(data);
        Response response = new Response();
        String res = "Received data :: " + data;
        response.setResponse(res);
        return response;
    }

    @GetMapping("/get-canvas")
    public ResponseEntity<?> getAllCanvasData(){
        log.info("Getting canvas Data...");
        List<CanvasData> data =  canvasService.getAllCanvasData();
        return ResponseEntity.ok(data);

    }

    @PostMapping("/run-simulation")
    public Response RunSimulationJob(@RequestBody CanvasData data){
        log.info("Saving canvas data");

        log.info("Starting simulation job");
        // will start the whole process of creating Tree and and its implementation, meanwhile
        // status would be set to IN_PROGRESS

        return null;

    }


}
