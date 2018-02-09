package de.itemis.mosig.racecar.textconv;

import org.junit.Test;

import java.nio.file.Paths;

public class TextFileReaderTest {

    @Test
    public void shouldReadContentsOfExistingFile() {
        new TextFileReader(Paths.get(""));
    }
}
