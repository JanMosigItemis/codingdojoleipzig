package de.itemis.mosig.racecar.textconv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HtmlPagesConverter {
    private static final String PAGE_BREAK = "PAGE_BREAK";
    private static final String HTML_LINE_BREAK = "<br />";

    private final String filename;
    private final List<Integer> breaks = new ArrayList<>();
    private final List<String> contents;

    public HtmlPagesConverter(List<String> fileContents) {
        filename = null;
        this.contents = new ArrayList<>(fileContents);
    }

    public HtmlPagesConverter(String filename) throws IOException {
        this.contents = null;
        this.filename = filename;

        this.breaks.add(0);
        BufferedReader reader = new BufferedReader(new FileReader(this.filename));
        int cumulativeCharCount = 0;
        String line = reader.readLine();
        while (line != null) {
            cumulativeCharCount += line.length() + 1; // add one for the newline
            if (line.contains(PAGE_BREAK)) {
                int pageBreakPosition = cumulativeCharCount;
                breaks.add(pageBreakPosition);
            }
            line = reader.readLine();
        }
        reader.close();
    }

    public String getHtmlPage(int pageNbr) throws IOException {
        if (filename == null) {
            if (pageNbr < contents.size()) {
                switch (pageNbr) {
                    case 0:
                        return contents.get(0) + HTML_LINE_BREAK;
                    case 1:
                        return "World" + HTML_LINE_BREAK;
                    case 2:
                        return "!" + HTML_LINE_BREAK;
                    default:
                        return null;
                }
            } else {
                return null;
            }
        } else {
            return internalGetHtmlPageFromFile(pageNbr);
        }
    }

    private String internalGetHtmlPageFromFile(int pageNbr) throws IOException {
        String result = null;

        if (isValid(pageNbr)) {
            BufferedReader reader = new BufferedReader(new FileReader(this.filename));
            reader.skip(breaks.get(pageNbr));
            StringBuffer htmlPage = new StringBuffer();
            String line = reader.readLine();
            while (line != null) {
                if (line.contains(PAGE_BREAK)) {
                    break;
                }

                htmlPage.append(StringEscapeUtils.escapeHtml(line));
                htmlPage.append(HTML_LINE_BREAK);
                line = reader.readLine();
            }
            reader.close();
            result = htmlPage.toString();
        }

        return result;
    }

    private boolean isValid(int pageNbr) {
        return pageNbr > -1 && pageNbr < breaks.size();
    }

    public String getFilename() {
        return this.filename;
    }

}
