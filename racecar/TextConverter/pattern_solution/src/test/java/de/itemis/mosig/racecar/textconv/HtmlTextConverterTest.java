package de.itemis.mosig.racecar.textconv;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HtmlTextConverterTest {

    private HtmlTextConverter underTest;

    @Before
    public void setUp() {
        underTest = new HtmlTextConverter(new TextFileReader(null));
    }

    @Test
    public void shouldConvertLeftAngelBracketsToHtmlCounterParts() {
        assertConvertResult("<","&lt;");
    }

    @Test
    public void shouldConvertRightAngelBracketsToHtmlCounterParts() {
        assertConvertResult(">","&gt;");
    }

    @Test
    public void shouldConvertAmpsToHtmlCounterParts() {
        assertConvertResult("&","&amp;");
    }

    @Test
    public void shouldConvertDoubleQuotesToHtmlCounterParts() {
        assertConvertResult("\"","&quot;");
    }

    @Test
    public void shouldConvertSingleQuotesToHtmlCounterParts() {
        assertConvertResult("'","&#39;");
    }

    @Test
    public void shouldNotConvertNonEscapableCharacters() {
        String input = "A";

        assertConvertResult(input,input);
    }

    @Test
    public void shouldConvertMultipleCharactersAtOnce() {
        String input = "ABC\"\"\"\"&&DE&\"'FGH\n\tIJKL<<>>A>'";
        String expectedOutput = "ABC&quot;&quot;&quot;&quot;&amp;&amp;DE&amp;&quot;&#39;FGH\n\tIJKL&lt;&lt;&gt;&gt;A&gt;&#39;";

        assertConvertResult(input,expectedOutput);
    }

    /*
     * ##### start private helper code #####
     */

    private void assertConvertResult(String input, String expectedOutput) {
        String result = underTest.convertToHtml(input);
        Assert.assertEquals(expectedOutput,result);
    }
}
