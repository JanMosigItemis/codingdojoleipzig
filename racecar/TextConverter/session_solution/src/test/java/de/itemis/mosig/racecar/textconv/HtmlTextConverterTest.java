package de.itemis.mosig.racecar.textconv;

import static org.junit.Assert.*;

import org.junit.Test;

public class HtmlTextConverterTest {
    @Test
    public void foo() {
        HtmlTextConverter converter = new HtmlTextConverter("foo");
        assertEquals("fixme", converter.getFilename());
    }
}
