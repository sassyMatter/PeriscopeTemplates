package com.app.controllers;



import com.app.models.Response;
import com.app.models.canvas.CanvasData;
import com.app.models.canvasSchema.TreeNode;
import com.app.services.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/canvas")
@CrossOrigin(origins="*")
@Slf4j
public class CanvasController {

    @Autowired
    CanvasService canvasService;

    @Autowired
    TreeBuilderService treeBuilderService;

    @Autowired
    CodeWriterService codeWriterService;

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


        // testing component's with code-writer

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("String", "var");
        requestBody.put("Response", "response");

        RestComponent restComponent = RestComponent
                                     .builder()
                .apiType("inbound")
                .url("/ge")
                .requestBody(requestBody)
                .headers(new HashMap<>())
                .httpMethod("PostMapping")
                .methodName("sumOfNumbers")
                .requestUrl("/test")
                .type("rest")
                .build();

        String generatedCode = restComponent.generateCode();
        log.info("Generated Code:: {} ", generatedCode);

        codeWriterService.writeToFile(generatedCode, "rest");

        FunctionComponent functionComponent = FunctionComponent
                .builder()
                .functionBody("int x = 7; \n int y = 8; \n System.out.println(x+y); return x+y;")
                .parameters(requestBody)
                .functionName("sumOfNumbers")
                .returnType("int")
                .build();

        String functionCode = functionComponent.generateCode();
        log.info("Generated Code for function:: {} ", functionCode);
        codeWriterService.writeToFile(functionCode, "function");


        Response res = Response.builder().response(generatedCode + "\n" + functionCode).build();
        return res;
    }


}
