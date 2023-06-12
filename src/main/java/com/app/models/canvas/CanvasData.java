package com.app.models.canvas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class CanvasData {
    public String version;
    public ArrayList<CanvasObject> objects;
}
