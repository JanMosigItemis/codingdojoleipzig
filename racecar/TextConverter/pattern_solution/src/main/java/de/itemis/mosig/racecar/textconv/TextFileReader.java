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
        if (Files.isReadable(filePath)) {
            try {
                return new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("File '" + filePath + "' does not exist.");
        }
        return null;
    }
}
