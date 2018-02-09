package de.itemis.mosig.racecar.textconv;

public class HtmlTextConverter {

    private final TextFileReader reader;

    public HtmlTextConverter(TextFileReader reader) {
        this.reader = reader;
    }

    public String convertToHtml() {
        String input = reader.contents();
        return StringEscapeUtils.escapeHtml(input);
    }
}
