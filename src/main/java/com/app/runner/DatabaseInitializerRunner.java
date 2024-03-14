package com.app.runner;

import com.app.utils.DatabaseInitializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DatabaseInitializerRunner implements CommandLineRunner {

    private final DatabaseInitializer databaseInitializer;

    public DatabaseInitializerRunner(DatabaseInitializer databaseInitializer) {
        this.databaseInitializer = databaseInitializer;
    }

    @Override
    public void run(String... args) throws Exception {
        // Pass the file path containing table definitions to create
        try {
            databaseInitializer.initializeDatabaseFromFile("initializer.sql");
        }catch(Exception ex){
            log.error("Could not configure database tables");
            ex.printStackTrace();
        }
    }
}
