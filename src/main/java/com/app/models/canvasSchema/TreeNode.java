package com.app.models.canvasSchema;

import com.app.models.canvas.CanvasObject;
import com.app.models.canvas.Connection;

import java.util.List;

public class TreeNode {

    public CanvasObject data;

    public List<TreeNode> connections;

     public TreeNode(CanvasObject data){
        this.data = data;
    }

}
