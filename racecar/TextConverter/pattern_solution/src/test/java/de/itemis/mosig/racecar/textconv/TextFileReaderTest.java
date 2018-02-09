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

    private static final String[] VALID_CHARACTERS = new String[] {"\n","\t","A","B","C","D","E","F","G","H","I","J","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private static int FILE_SIZE_IN_CHARACTERS = 100;

    private Random random;
    private Path tmpFilePath;

    @Before
    public void setUp() {
        random = new Random();

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
    public void shouldReadContentsOfExistingFile() {
        String expectedContent = generateRandomString();
        writeToTestFile(expectedContent);
        TextFileReader underTest = new TextFileReader(tmpFilePath);

        String result = underTest.contents();

        Assert.assertEquals(expectedContent, result);
    }

    @Test
    public void shouldReadContentsOfEmptyFile() {
        TextFileReader underTest = new TextFileReader(tmpFilePath);

        String result = underTest.contents();

        Assert.assertEquals("", result);
    }

    @Test
    public void shouldThrowExceptionIfFileDoesNotExist() {
        Path nonExistingPath = tmpFilePath.resolve("non_existing");
        TextFileReader underTest = new TextFileReader(nonExistingPath);

        try {
            underTest.contents();
            Assert.fail("Did not throw expected exception.");
        } catch (Exception e) {
            Assert.assertEquals("File '" + nonExistingPath + "' does not exist.", e.getMessage());
        }
    }

    /*
     * ##### start private helper code
     */

    private void writeToTestFile(String contents) {
        try {
            Files.write(tmpFilePath, contents.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            Assert.fail("Could not write to temporary test file: " + e.getMessage());
        }
    }

    private String generateRandomString() {
        int nbrOfCharacters = random.nextInt(FILE_SIZE_IN_CHARACTERS);

        String result = "";
        for(int i=0;i<nbrOfCharacters;i++) {
            int rndCharacterIndex = random.nextInt(VALID_CHARACTERS.length);
            result += VALID_CHARACTERS[rndCharacterIndex];
        }

        return result;
    }
}
