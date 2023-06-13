package com.app.services.interfaces;

import com.app.models.canvas.CanvasData;
import com.app.models.canvas.CanvasObject;
import com.app.models.canvasSchema.TreeNode;

import java.util.List;

public interface TreeBuilderService {

    public TreeNode buildTree(CanvasData objects);
    public List<TreeNode> convertObjects(CanvasData oldObjects);

    public TreeNode createNewObject(CanvasObject canvasObject);


}
