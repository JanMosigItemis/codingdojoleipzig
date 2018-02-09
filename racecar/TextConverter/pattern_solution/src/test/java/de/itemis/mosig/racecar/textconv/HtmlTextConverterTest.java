package de.itemis.mosig.racecar.textconv;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class HtmlTextConverterTest {

    @Test
    public void shouldConvertLeftAngelBracketsToHtmlCounterParts() {
        HtmlTextConverter underTest = new HtmlTextConverter();

        String result = underTest.convertToHtml("<" );

        Assert.assertEquals("&lt;",result);
    }

    @Test
    public void shouldConvertRightAngelBracketsToHtmlCounterParts() {
        HtmlTextConverter underTest = new HtmlTextConverter();

        String result = underTest.convertToHtml(">" );

        Assert.assertEquals("&gt;",result);
    }

    @Test
    public void shouldConvertAmpsToHtmlCounterParts() {
        HtmlTextConverter underTest = new HtmlTextConverter();

        String result = underTest.convertToHtml("&" );

        Assert.assertEquals("&amp;",result);
    }

    @Test
    public void shouldConvertDoubleQuotesToHtmlCounterParts() {
        HtmlTextConverter underTest = new HtmlTextConverter();

        String result = underTest.convertToHtml("\"" );

        Assert.assertEquals("&quot;",result);
    }

    @Test
    public void shouldConvertSingleQuotesToHtmlCounterParts() {
        HtmlTextConverter underTest = new HtmlTextConverter();

        String result = underTest.convertToHtml("'" );

        Assert.assertEquals("&#39;",result);
    }

    @Test
    public void shouldNotConvertNonEscapableCharacters() {
        String input = "A";
        HtmlTextConverter underTest = new HtmlTextConverter();

        String result = underTest.convertToHtml(input);

        Assert.assertEquals(input,result);
    }

    @Test
    public void shouldConvertMultipleCharactersAtOnce() {
        String input = "ABC\"\"\"\"&&DE&\"'FGH\n\tIJKL<<>>A>'";
        String expectedOutput = "ABC&quot;&quot;&quot;&quot;&amp;&amp;DE&amp;&quot;&#39;FGH\n\tIJKL&lt;&lt;&gt;&gt;A&gt;&#39;";
        HtmlTextConverter underTest = new HtmlTextConverter();

        String result = underTest.convertToHtml(input);

        Assert.assertEquals(expectedOutput,result);
    }
}
