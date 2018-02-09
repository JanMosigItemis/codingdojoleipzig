package de.itemis.mosig.racecar.textconv;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextFileReader {

    private final Path filePath;

    public TextFileReader(Path filePath) {
        this.filePath = filePath;
    }

    public String contents() {
        String result = null;
            try {
                result = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new RuntimeException("Error while reading file '" + filePath + "'.", e);
            }
        return result;
    }
}
