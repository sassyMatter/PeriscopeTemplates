package com.app.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;
public class ParseYAML {
    private static final String APPLICATION_YML_PATH = "application.yml";

    public static void writeAppName(String appName) throws IOException {
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> springConfig = new HashMap<>();
        springConfig.put("name", appName);
        data.put("spring", springConfig);

        try (FileOutputStream outputStream = new FileOutputStream(APPLICATION_YML_PATH)) {
            new Yaml().dump(data);
        } catch (FileNotFoundException e) {
            // Create the file if it doesn't exist
            File file = new File(APPLICATION_YML_PATH);
            if (file.createNewFile()) {
                System.out.println("Created application.yml file");
                writeAppName(appName);  // Recursive call to write data after creating file
            } else {
                throw new IOException("Failed to create application.yml file");
            }
        }
    }
}
