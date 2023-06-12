package com.app.models.canvas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CanvasData {
    public String version;
    public ArrayList<CanvasObject> canvasObjects;
}
