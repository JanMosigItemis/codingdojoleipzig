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
    private final List<String> rawContents;
    private List<String> contents = new ArrayList<>();

    public HtmlPagesConverter(List<String> fileContents) {
        filename = null;
        this.rawContents = new ArrayList<>(fileContents);

        if (!rawContents.isEmpty() && rawContents.get(0).equals(PAGE_BREAK)) {
            rawContents.add(0, "");
        }
        if (!rawContents.isEmpty() && rawContents.get(rawContents.size() - 1).equals(PAGE_BREAK)) {
            rawContents.add("");
        }

        for (int i = 0; i < rawContents.size(); i++) {
            String line = rawContents.get(i);
            if (line.equals(PAGE_BREAK)) {
                if (i < rawContents.size() - 1 && rawContents.get(i + 1).equals(PAGE_BREAK)) {
                    contents.add("");
                }
                continue;
            }
            contents.add(line);
        }
    }

    public HtmlPagesConverter(String filename) throws IOException {
        this.rawContents = null;
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
                if (pageNbr > -1) {
                    return noLineBreakAfterEmptyPage(contents.get(pageNbr));
                } else {
                    return null;
                }
            } else if (firstPageOfEmptyContentIsRequested(pageNbr)) {
                return "";
            } else {
                return null;
            }
        } else {
            return internalGetHtmlPageFromFile(pageNbr);
        }
    }

    private String ignorePageBreaks(int pageNbr) {
        if (pageNbr < rawContents.size()) {
            if (rawContents.get(pageNbr).equals(PAGE_BREAK)) {
                return ignorePageBreaks(++pageNbr);
            } else {
                return rawContents.get(pageNbr);
            }
        } else {
            return "";
        }
    }

    private boolean pageAfterLastPageIsRequestedAndLastPageContainsAPageBreak(int pageNbr) {
        int lastPageNbr = rawContents.size() - 1;
        return pageNbr == (lastPageNbr + 1) && rawContents.get(lastPageNbr).equals(PAGE_BREAK);
    }

    private String noLineBreakAfterEmptyPage(String pageContent) {
        return pageContent.isEmpty() ? pageContent : pageContent + HTML_LINE_BREAK;
    }

    private boolean firstPageOfEmptyContentIsRequested(int pageNbr) {
        return contents.isEmpty() && pageNbr == 0;
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
