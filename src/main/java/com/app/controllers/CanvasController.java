package com.app.controllers;



import com.app.models.Response;
import com.app.models.canvas.CanvasData;
import com.app.models.canvasSchema.TreeNode;
import com.app.services.CanvasService;
import com.app.services.TreeBuilderService;
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

    @Autowired
    TreeBuilderService treeBuilderService;

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

        // 1. Get Data, for now from DB
        // 2. Create tree
        // 3. Print Tree with processing each node
        // 4. Start writing components: and codeGenerator for same


        return null;

    }

    @PostMapping("/test")
    public Response testController(){

        List<CanvasData> dataList = canvasService.getAllCanvasData();

        for(int i = 0; i < dataList.size(); i++){

            CanvasData data = dataList.get(i);
            TreeNode root = treeBuilderService.buildTree(data);

            log.info("root :: {}", root);

            treeBuilderService.traverseTree(root);

        }

        return null;
    }


}
