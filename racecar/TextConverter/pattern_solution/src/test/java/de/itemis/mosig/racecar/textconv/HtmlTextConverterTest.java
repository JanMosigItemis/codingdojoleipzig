package de.itemis.mosig.racecar.textconv;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class HtmlTextConverterTest {

    private TextFileReader fileReaderMock;

    private HtmlTextConverter underTest;

    @Before
    public void setUp() {
        fileReaderMock = Mockito.mock(TextFileReader.class);
        underTest = new HtmlTextConverter(fileReaderMock);
    }

    @Test
    public void shouldConvertLeftAngelBracketsToHtmlCounterParts() {
        assertConvertResult("<", "&lt;");
    }

    @Test
    public void shouldConvertRightAngelBracketsToHtmlCounterParts() {
        assertConvertResult(">", "&gt;");
    }

    @Test
    public void shouldConvertAmpsToHtmlCounterParts() {
        assertConvertResult("&", "&amp;");
    }

    @Test
    public void shouldConvertDoubleQuotesToHtmlCounterParts() {
        assertConvertResult("\"", "&quot;");
    }

    @Test
    public void shouldConvertSingleQuotesToHtmlCounterParts() {
        assertConvertResult("'", "&#39;");
    }

    @Test
    public void shouldNotConvertNonEscapableCharacters() {
        String input = "A";

        assertConvertResult(input, input);
    }

    @Test
    public void shouldConvertMultipleCharactersAtOnce() {
        String input = "ABC\"\"\"\"&&DE&\"'FGH\n\tIJKL<<>>A>'";
        String expectedOutput = "ABC&quot;&quot;&quot;&quot;&amp;&amp;DE&amp;&quot;&#39;FGH\n\tIJKL&lt;&lt;&gt;&gt;A&gt;&#39;";

        assertConvertResult(input, expectedOutput);
    }

    /*
     * ##### start private helper code #####
     */

    private void assertConvertResult(String input, String expectedOutput) {
        Mockito.when(fileReaderMock.contents()).thenReturn(input);

        String result = underTest.convertToHtml();
        Assert.assertEquals(expectedOutput, result);
    }
}
