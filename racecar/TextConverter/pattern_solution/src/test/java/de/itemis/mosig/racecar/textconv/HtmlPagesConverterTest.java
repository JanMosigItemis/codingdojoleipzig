package de.itemis.mosig.racecar.textconv;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class HtmlPagesConverterTest {

    private static final String PAGE_BREAK = "PAGE_BREAK";

    private Set<Path> pathsOfTestFiles = new HashSet<>();

    @After
    public void cleanupExistingTestFile() {
        pathsOfTestFiles.forEach(this::removeTestFile);
    }

    @Test
    public void shouldInsertOneBrForEachPageBreakEncounteredAndOneForTheLastLine() {
        HtmlPagesConverter underTest = prepareUnderTest("Hello", "World", "!");

        assertPageContents(underTest, "Hello<br />", "World<br />", "!<br />");
    }

    @Test
    public void shouldReturnFullFileContentsIfThereAreNoPageBreaks() throws IOException {
        HtmlPagesConverter underTest = prepareUnderTest("Hello World!");

        assertPageContents(underTest, "Hello World!<br />");
    }

    @Test
    public void shouldReturnEmptyStringOnEmptyFile() throws IOException {
        HtmlPagesConverter underTest = prepareUnderTest();

        assertPageContents(underTest, "");
    }

    @Test
    public void shouldReturnEmptyStringIfPageIsNegative() throws IOException {
        HtmlPagesConverter underTest = prepareUnderTest("");

        String result = underTest.getHtmlPage(-1);
        Assert.assertNull(getPage(underTest, -1));
    }

    @Test
    public void shouldReturnTwoEmptyPagesIfFileContainsOnePageBreak() throws IOException {
        HtmlPagesConverter underTest = prepareUnderTestOld("only_one_page_break.txt");

        assertPageContents(underTest, "", "");
    }

    @Test
    public void shouldReturnFourEmptyPagesIfFileContainsThreePageBreaks() throws IOException {
        HtmlPagesConverter underTest = prepareUnderTestOld("only_multiple_page_breaks.txt");

        assertPageContents(underTest, "", "", "", "");
    }

    @Test
    public void shouldReturnFileNameIfFileExists() throws IOException {
        Path testFilePath = prepareTestFile("multiple_page_breaks.txt");
        String expectedResult = testFilePath.toString();
        HtmlPagesConverter underTest = new HtmlPagesConverter(expectedResult);

        String result = underTest.getFilename();

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void shouldThrowIoExceptionIfFileDoesNotExist() {
        String nonExistingFileName = "this_file_does_not_exist";
        try {
            new HtmlPagesConverter(nonExistingFileName);
            Assert.fail();
        } catch (IOException e) {
            Assert.assertEquals(FileNotFoundException.class.getSimpleName(), e.getClass().getSimpleName());
            Assert.assertTrue(e.getMessage().contains(nonExistingFileName));
        }
    }

    /*
     * ##### Start private helper code #####
     */

    private void fail(String msg, Throwable cause) {
        Assert.fail(msg + " - " + cause.getClass().getSimpleName() + ": " + cause.getMessage());
    }

    private void assertPageContents(HtmlPagesConverter underTest, String... contents) {
        for (int i = 0; i < contents.length; i++) {
            String expectedContent = contents[i];
            String actualContent = getPage(underTest, i);
            Assert.assertEquals(expectedContent, actualContent);
        }

        Assert.assertNull(getPage(underTest, contents.length));
    }

    private String getPage(HtmlPagesConverter underTest, int pageNbr) {
        String result = null;

        try {
            result = underTest.getHtmlPage(pageNbr);
        } catch (IOException e) {
            fail("Could not read from test instance", e);
        }

        return result;
    }

    private HtmlPagesConverter prepareUnderTest(String... fileContents) {
        return new HtmlPagesConverter(Arrays.asList(fileContents));
    }

    private HtmlPagesConverter prepareUnderTestOld(String resourceName) {
        String filePath = prepareTestFile(resourceName).toString();

        HtmlPagesConverter result = null;
        try {
            result = new HtmlPagesConverter(filePath);
        } catch (IOException e) {
            fail("Could not create instance of '" + HtmlPagesConverter.class.getSimpleName() + "'", e);
        }

        return result;
    }

    private Path prepareTestFile(String resourceName) {
        resourceName = "/" + resourceName.trim();
        Path result = null;

        try (InputStream srcFileStream = getClass().getResourceAsStream(resourceName)) {
            Assert.assertNotNull(srcFileStream);
            result = Files.createTempFile(this.getClass().getSimpleName(), "tmp");
            Files.copy(srcFileStream, result, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            fail("Could not copy resource '" + resourceName + "' to temporary location", e);
        }

        pathsOfTestFiles.add(result);
        return result;
    }

    private void removeTestFile(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            System.err.println("WARN - Could not remove temporary file: " + path);
        }
    }
}