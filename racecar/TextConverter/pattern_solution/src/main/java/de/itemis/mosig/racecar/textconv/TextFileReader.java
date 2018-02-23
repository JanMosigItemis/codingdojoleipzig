package de.itemis.mosig.racecar.textconv;

import com.google.common.base.Splitter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

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

    public List<String> contentsAsList() {
        List<String> result = Splitter.onPattern("\\r?\\n|\\r").splitToList(contents());
        return Collections.unmodifiableList(result);
    }

    public Path getFilePath() {
        return filePath;
    }
}
