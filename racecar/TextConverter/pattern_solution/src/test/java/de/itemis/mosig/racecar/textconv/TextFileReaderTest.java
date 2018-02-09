package de.itemis.mosig.racecar.textconv;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class TextFileReaderTest {

    @Test
    public void shouldReadContentsOfExistingFile() throws IOException {
        Path tmpFilePath = Files.createTempFile(this.getClass().getSimpleName(),"txt");
        String expectedContent = new String(new byte[]{(byte) (char) (new Random().nextInt(40) + 20)}, StandardCharsets.UTF_8);
        Files.write(tmpFilePath, expectedContent.getBytes(StandardCharsets.UTF_8));

        TextFileReader underTest = new TextFileReader(tmpFilePath);

        String result = underTest.contents();

        Assert.assertEquals(expectedContent, result);
    }
}
