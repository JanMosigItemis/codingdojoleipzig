package de.itemis.mosig.racecar.textconv;

public class HtmlTextConverter {

    public HtmlTextConverter(TextFileReader reader) {

    }

    public String convertToHtml(String input) {

        return StringEscapeUtils.escapeHtml(input);
    }
}
