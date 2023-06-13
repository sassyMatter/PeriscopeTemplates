package com.app.models.canvasSchema;

import com.app.models.canvas.CanvasObject;
import com.app.models.canvas.Connection;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class  TreeNode {

    public CanvasObject data;

    public List<TreeNode> connections;

     public TreeNode(CanvasObject data){
        this.data = data;
        this.connections = new ArrayList<>();
    }

    public void addChild(TreeNode node){
         connections.add(node);
    }

}
