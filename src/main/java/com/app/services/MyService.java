package com.app.services;

import com.app.controllers.Converter;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.ListIterator;

@Service
public class MyService {

    public void writeToFile(String input, String type) {
        try {

            String filePath = null;
            if("rest".equals(type)){
                 filePath = Converter.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            }else {
                 filePath = MyService.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            }


            Path file = Path.of(filePath);
            List<String> lines = Files.readAllLines(file);

            // Find the index of the last closing curly brace
            int lastClosingBraceIndex = findLastClosingBraceIndex(lines);

            // Validate the index
            if (lastClosingBraceIndex == -1) {
                throw new IllegalStateException("Closing brace not found in the file");
            }

            // Insert the input string just before the last closing curly brace
            lines.add(lastClosingBraceIndex, input);

            // Write the modified content back to the file
            Files.write(file, lines, StandardOpenOption.TRUNCATE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int findLastClosingBraceIndex(List<String> lines) {
        ListIterator<String> iterator = lines.listIterator(lines.size());

        while (iterator.hasPrevious()) {
            String line = iterator.previous().trim();
            if (line.equals("}")) {
                return iterator.nextIndex();
            }
        }

        return -1;
    }

}
