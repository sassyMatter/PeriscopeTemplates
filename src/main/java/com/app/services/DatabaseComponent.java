package com.app.services;

import com.app.services.interfaces.CodeComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseComponent implements CodeComponent {
    private final List<String> tableDefinitions;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public DatabaseComponent(List<String> tableDefinitions) {
        this.tableDefinitions = tableDefinitions;
    }

    @Override
    public String generateCode() {
        // Execute DDL statements using JdbcTemplate
        StringBuilder codeBuilder = new StringBuilder();

        try {
            for (String tableDefinition : tableDefinitions) {
                jdbcTemplate.execute(tableDefinition);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute DDL statements", e);
        }

        return codeBuilder.toString();
    }
}
