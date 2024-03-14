package com.app.utils;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class DatabaseInitializer {

    private final JdbcTemplate jdbcTemplate;
    private final ResourceLoader resourceLoader;

    @Autowired
    ScriptLoader scriptLoader;

    public DatabaseInitializer(JdbcTemplate jdbcTemplate, ResourceLoader resourceLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.resourceLoader = resourceLoader;
    }

    public void initializeDatabaseFromFile(String filePath) throws IOException {
        List<String> tableDefinitions = readTableDefinitionsFromFile(filePath);
        log.info("loaded table definitions {} ", tableDefinitions);
        for (String tableDefinition : tableDefinitions) {
            executeTableDefinition(tableDefinition);
        }
    }

    private List<String> readTableDefinitionsFromFile(String filePath) throws IOException {
        List<String> tableDefinitions = new ArrayList<>();
        File file = scriptLoader.loadScriptFile(filePath);
        log.info("File loaded is {} ", file);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
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
            log.info("Executing table definitions..");
            jdbcTemplate.execute(tableDefinition);
            log.info("Execution Success: Database initialized..");
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute DDL statement: " + tableDefinition, e);
        }
    }

    private Resource getResource(String filePath) {
        return resourceLoader.getResource("classpath:" + filePath);
    }
}
