package de.itemis.mosig.racecar.textconv;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


public class HtmlPagesConverterTest {

    private Path pathToFileWithNoPageBreaks;

    @Before
    public void setUp() {
        try (InputStream srcFileStream = getClass().getResourceAsStream("/no_page_breaks.txt")) {
            Assert.assertNotNull(srcFileStream);
            pathToFileWithNoPageBreaks = Files.createTempFile(this.getClass().getSimpleName(), "txt");
            Files.copy(srcFileStream, pathToFileWithNoPageBreaks, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            Assert.fail("Could not copy resource file to temporary location - " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        if (pathToFileWithNoPageBreaks != null) {
            try {
                Files.deleteIfExists(pathToFileWithNoPageBreaks);
            } catch (IOException e) {
                System.err.println("WARN - Could not remove temporary file: " + pathToFileWithNoPageBreaks);
            }
        }
    }

    @Test
    public void shouldReturnFullFileContentsIfThereAreNoPageBreaks() throws IOException {
        HtmlPagesConverter converter = new HtmlPagesConverter(pathToFileWithNoPageBreaks.toString());
    }
}
