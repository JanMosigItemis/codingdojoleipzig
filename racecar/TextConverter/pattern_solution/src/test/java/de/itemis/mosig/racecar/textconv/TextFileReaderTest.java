package de.itemis.mosig.racecar.textconv;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class TextFileReaderTest {

    private Path tmpFilePath;

    @Before
    public void setUp() {
        try {
            tmpFilePath = Files.createTempFile(this.getClass().getSimpleName(),"txt");
        } catch (IOException e) {
            Assert.fail("Could not create temporary test file: " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        if (tmpFilePath != null) {
            try {
                Files.deleteIfExists(tmpFilePath);
            } catch (IOException e) {
                System.err.println("WARNING: Could not delete temporary test file: " + e.getMessage());
            }
        }
    }
    @Test
    public void shouldReadContentsOfExistingFile() throws IOException {
        String expectedContent = generateRandomString();
        Files.write(tmpFilePath, expectedContent.getBytes(StandardCharsets.UTF_8));

        TextFileReader underTest = new TextFileReader(tmpFilePath);

        String result = underTest.contents();

        Assert.assertEquals(expectedContent, result);
    }

    /*
     * ##### start private helper code
     */

    private String generateRandomString() {
        return new String(new byte[]{(byte) (char) (new Random().nextInt(40) + 20)}, StandardCharsets.UTF_8);
    }
}
