package com.app.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Slf4j
@Component
public class ScriptLoader {

    private final ResourceLoader resourceLoader;

    public ScriptLoader(ResourceLoader resourceLoader) {
        log.info("test123");
        this.resourceLoader = resourceLoader;
    }



    public File loadScriptFile(String scriptName) throws IOException {


//        System.out.println("message2");
        String address="classpath:scripts/" + scriptName;
        log.info("Address {} " , address);

        log.info(System.getProperty("java.class.path"));

        try (InputStream inputStream = resourceLoader.getResource(address).getInputStream()) {
            log.info("source dir found");
            File tempFile = File.createTempFile(scriptName, null);

            Files.copy(inputStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return tempFile;
        }
        catch(Error e){
            log.info("error",e);
        }
        log.info("project sourcedir not found");
        return null;
    }
}