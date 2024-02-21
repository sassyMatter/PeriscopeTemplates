package com.app.runner;

import com.app.utils.DatabaseInitializer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializerRunner implements CommandLineRunner {

    private final DatabaseInitializer databaseInitializer;

    public DatabaseInitializerRunner(DatabaseInitializer databaseInitializer) {
        this.databaseInitializer = databaseInitializer;
    }

    @Override
    public void run(String... args) throws Exception {
        // Pass the file path containing table definitions to create
        databaseInitializer.initializeDatabaseFromFile("src/main/resources/scripts/initializer.sql");
    }
}
