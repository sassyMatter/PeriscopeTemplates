package com.app.services;

import com.app.models.canvas.CanvasData;
import com.app.models.canvas.CanvasObject;
import com.app.models.canvas.Connection;
import com.app.models.canvasSchema.TreeNode;
import com.sun.source.tree.Tree;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TreeBuilderService {

    public TreeNode buildTree(CanvasData objects){

        List<TreeNode> treeNodes = convertObjects(objects);
//      populating child nodes for each node and setting up the root for the same
//      Map to hold canvas objects
        TreeNode root = null;
        Map<String, TreeNode> treeNodeMap = new HashMap<>();

        for(int i = 0; i < treeNodes.size(); i++){
            TreeNode node = treeNodes.get(i);
            treeNodeMap.put(node.getData().getId(), node);
            if(node.getData().getType() == "input"){
                root = node;
            }
        }

        for(int i = 0; i < treeNodes.size(); i++){
            TreeNode node = treeNodes.get(i);
            List<Connection> connections = node.data.getConnections();

            connections.forEach(id ->{
               node.addChild(treeNodeMap.get(id));
            });

        }

        return root;
    }


    public  List<TreeNode> convertObjects(CanvasData oldObjects) {
        List<TreeNode> newObjects = oldObjects.getObjects().stream()
                .map(this::createNewObject)
                .collect(Collectors.toList());
        return newObjects;
    }

    public TreeNode createNewObject(CanvasObject canvasObject){
        return new TreeNode(canvasObject);
    }




}
