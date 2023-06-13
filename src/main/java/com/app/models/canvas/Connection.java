package com.app.models.canvas;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document
public class Connection{
    public String id;
}
