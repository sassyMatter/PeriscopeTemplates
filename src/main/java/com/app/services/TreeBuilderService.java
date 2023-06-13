package com.app.services;

import com.app.models.canvas.CanvasData;
import com.app.models.canvas.CanvasObject;
import com.app.models.canvasSchema.TreeNode;
import com.sun.source.tree.Tree;

import java.util.List;
import java.util.stream.Collectors;

public class TreeBuilderService {

    public TreeNode buildTree(CanvasData objects){

        List<TreeNode> treeNodes = convertObjects(objects);

        return null;
    }


    public  List<TreeNode> convertObjects(CanvasData oldObjects) {
        List<TreeNode> newObjects = oldObjects.getObjects().stream()
                .map(m -> createNewObject(m))
                .collect(Collectors.toList());
        return newObjects;
    }

    public TreeNode createNewObject(CanvasObject canvasObject){
        return new TreeNode(canvasObject);
    }




}
