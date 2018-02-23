package de.itemis.mosig.racecar.textconv;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;


public class HtmlPagesConverterTest {

    private static final String PAGE_BREAK = "PAGE_BREAK";

    @Test
    public void shouldInsertOneBrForEachPageBreakEncounteredAndOneForTheLastLine() {
        HtmlPagesConverter underTest = prepareUnderTest("Hello", PAGE_BREAK, "World", PAGE_BREAK, "!");

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
        Assert.assertNull(result);
    }

    @Test
    public void shouldReturnTwoEmptyPagesIfFileContainsOnePageBreakAndNothingElse() throws IOException {
        HtmlPagesConverter underTest = prepareUnderTest(PAGE_BREAK);

        assertPageContents(underTest, "", "");
    }

    @Test
    public void onOddNumberOfPageBreaksShouldReturnPageBreakCountPlusOneEmptyPages() throws IOException {
        HtmlPagesConverter underTest = prepareUnderTest(PAGE_BREAK, PAGE_BREAK, PAGE_BREAK);

        assertPageContents(underTest, "", "", "", "");
    }

    @Test
    public void onEvenNumberOfPageBreaksShouldReturnPageBreakCountPlusOneEmptyPages() throws IOException {
        HtmlPagesConverter underTest = prepareUnderTest(PAGE_BREAK, PAGE_BREAK);

        assertPageContents(underTest, "", "", "");
    }

    /*
     * ##### Start private helper code #####
     */

    private void assertPageContents(HtmlPagesConverter underTest, String... contents) {
        for (int i = 0; i < contents.length; i++) {
            String expectedContent = contents[i];
            String actualContent = underTest.getHtmlPage(i);
            Assert.assertEquals(expectedContent, actualContent);
        }

        Assert.assertNull(underTest.getHtmlPage(contents.length));
    }

    private HtmlPagesConverter prepareUnderTest(String... fileContents) {
        return new HtmlPagesConverter(Arrays.asList(fileContents));
    }
}