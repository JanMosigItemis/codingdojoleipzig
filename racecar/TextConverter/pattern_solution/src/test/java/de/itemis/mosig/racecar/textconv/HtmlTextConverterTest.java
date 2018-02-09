package de.itemis.mosig.racecar.textconv;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class HtmlTextConverterTest {

    @Test
    public void shouldConverAngelBracketsToHtmlCounterParts() {
        HtmlTextConverter underTest = new HtmlTextConverter();

        String result = underTest.convertToHtml("<>" );

        Assert.assertEquals("&lt;&gt;",result);
    }

    @Test
    public void shouldConverAmpsToHtmlCounterParts() {
        HtmlTextConverter underTest = new HtmlTextConverter();

        String result = underTest.convertToHtml("&" );

        Assert.assertEquals("&amp;",result);
    }

    @Test
    public void shouldConverDoubleQuotesToHtmlCounterParts() {
        HtmlTextConverter underTest = new HtmlTextConverter();

        String result = underTest.convertToHtml("\"" );

        Assert.assertEquals("&quot;",result);
    }
}
