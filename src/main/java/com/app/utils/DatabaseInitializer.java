package com.app.utils;



import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DatabaseInitializer {

    private final JdbcTemplate jdbcTemplate;
    private final ResourceLoader resourceLoader;

    public DatabaseInitializer(JdbcTemplate jdbcTemplate, ResourceLoader resourceLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.resourceLoader = resourceLoader;
    }

    public void initializeDatabaseFromFile(String filePath) {
        List<String> tableDefinitions = readTableDefinitionsFromFile(filePath);

        for (String tableDefinition : tableDefinitions) {
            executeTableDefinition(tableDefinition);
        }
    }

    private List<String> readTableDefinitionsFromFile(String filePath) {
        List<String> tableDefinitions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getResource(filePath).getInputStream()))) {
            String line;
            StringBuilder currentStatement = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                if (line.trim().endsWith(";")) {
                    currentStatement.append(line.trim(), 0, line.length() - 1);
                    tableDefinitions.add(currentStatement.toString());
                    currentStatement.setLength(0);
                } else {
                    currentStatement.append(line.trim()).append(" ");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading table definitions from file", e);
        }

        return tableDefinitions;
    }

    private void executeTableDefinition(String tableDefinition) {
        try {
            jdbcTemplate.execute(tableDefinition);
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute DDL statement: " + tableDefinition, e);
        }
    }

    private Resource getResource(String filePath) {
        return resourceLoader.getResource("classpath:" + filePath);
    }
}
