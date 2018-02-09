package de.itemis.mosig.racecar.textconv;

import org.junit.Test;

import java.io.IOException;


public class HtmlPagesConverterTest {
    @Test
    public void shouldReturnFullPageIfThereAreNoLineBreaks() throws IOException {
        HtmlPagesConverter converter = new HtmlPagesConverter(new TextFileReader(null));
    }
}
