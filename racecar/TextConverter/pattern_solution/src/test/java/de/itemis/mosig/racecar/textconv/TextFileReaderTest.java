package de.itemis.mosig.racecar.textconv;

import com.google.common.collect.Lists;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public class TextFileReaderTest {

    private static final String[] VALID_CHARACTERS = new String[]{"\n", "\t", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private Random random;
    private Path tmpFilePath;

    @Before
    public void setUp() {
        random = new Random();

        try {
            tmpFilePath = Files.createTempFile(this.getClass().getSimpleName(), "txt");
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
        TextFileReader underTest = createUnderTest(tmpFilePath);

        String result = underTest.contents();

        Assert.assertEquals(expectedContent, result);
    }

    @Test
    public void shouldCreateOneListEntryPerNewLine() {
        List<String> expectedResult = Lists.newArrayList("one", "two", "three");
        writeToTestFile(String.join("\n", expectedResult));
        TextFileReader underTest = createUnderTest(tmpFilePath);

        List<String> actualResult = underTest.contentsAsList();

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldCreateOneListEntryPerCarriageReturn() {
        List<String> expectedResult = Lists.newArrayList("one", "two", "three");
        writeToTestFile(String.join("\r", expectedResult));
        TextFileReader underTest = createUnderTest(tmpFilePath);

        List<String> actualResult = underTest.contentsAsList();

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldCreateOneListEntryPerCarriageReturnNewLine() {
        List<String> expectedResult = Lists.newArrayList("one", "two", "three");
        writeToTestFile(String.join("\r\n", expectedResult));
        TextFileReader underTest = createUnderTest(tmpFilePath);

        List<String> actualResult = underTest.contentsAsList();

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldReadContentsOfEmptyFile() {
        TextFileReader underTest = createUnderTest(tmpFilePath);

        String result = underTest.contents();

        Assert.assertEquals("", result);
    }

    @Test
    public void shouldThrowExceptionIfFileDoesNotExist() {
        Path nonExistingPath = tmpFilePath.resolve("non_existing");
        TextFileReader underTest = createUnderTest(nonExistingPath);

        try {
            underTest.contents();
            Assert.fail("Did not throw expected exception.");
        } catch (Exception e) {
            Assert.assertEquals("Error while reading file '" + nonExistingPath + "'.", e.getMessage());
        }
    }

    @Test
    public void shouldReturnFilePath() {
        TextFileReader underTest = createUnderTest(tmpFilePath);
        Path result = underTest.getFilePath();

        Assert.assertEquals(tmpFilePath, result);
    }
    /*
     * ##### start private helper code
     */

    private TextFileReader createUnderTest(Path filePath) {
        return new TextFileReader(filePath);
    }

    private void writeToTestFile(String contents) {
        try {
            Files.write(tmpFilePath, contents.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            Assert.fail("Could not write to temporary test file: " + e.getMessage());
        }
    }

    private String generateRandomString() {
        int fileSizeInCharacters = 100;

        int nbrOfCharacters = random.nextInt(fileSizeInCharacters);

        StringBuilder resultBuilder = new StringBuilder();
        for (int i = 0; i < nbrOfCharacters; i++) {
            int rndCharacterIndex = random.nextInt(VALID_CHARACTERS.length);
            resultBuilder.append(VALID_CHARACTERS[rndCharacterIndex]);
        }

        return resultBuilder.toString();
    }
}
