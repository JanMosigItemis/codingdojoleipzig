package de.itemis.mosig.racecar.textconv;

class StringEscapeUtils {

    public static String escapeHtml(String input) {
        String output = input;
        output = output.replace("&", "&amp;");
        output = output.replace("<", "&lt;");
        output = output.replace(">", "&gt;");
        output = output.replace("\"", "&quot;");
        output = output.replace("'", "&#39;");
        return output;
    }

}
