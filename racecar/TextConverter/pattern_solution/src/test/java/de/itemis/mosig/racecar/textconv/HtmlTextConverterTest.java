package de.itemis.mosig.racecar.textconv;

import static org.junit.Assert.*;

import org.junit.Test;

public class HtmlTextConverterTest {
    @Test
    public void shouldConverAngelBracketsToHtmlCounterParts() {
        HtmlTextConverter underTest = new HtmlTextConverter();

        underTest.convertToHtml("<>");
    }
}
