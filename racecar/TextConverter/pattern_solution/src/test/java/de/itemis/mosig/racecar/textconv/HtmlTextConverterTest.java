package de.itemis.mosig.racecar.textconv;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class HtmlTextConverterTest {

    @Test
    public void shouldConverAngelBracketsToHtmlCounterParts() throws IOException {
        HtmlTextConverter underTest = new HtmlTextConverter();

        String result = underTest.convertToHtml("<>" );

        Assert.assertEquals("&lt;&gt;",result);
    }
}
