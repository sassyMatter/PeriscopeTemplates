package com.app.services.interfaces;

import com.app.models.canvas.CanvasData;

import java.util.List;

public interface CanvasService {
    public CanvasData saveCanvasData(CanvasData canvasData);
    public List<CanvasData> getAllCanvasData();
}
