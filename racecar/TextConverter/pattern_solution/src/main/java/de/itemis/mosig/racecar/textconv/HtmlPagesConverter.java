package de.itemis.mosig.racecar.textconv;

import java.util.ArrayList;
import java.util.List;

public class HtmlPagesConverter {
    private static final String PAGE_BREAK = "PAGE_BREAK";
    private static final String HTML_LINE_BREAK = "<br />";

    private final List<String> contents;

    public HtmlPagesConverter(List<String> fileContents) {
        List<String> rawContents = new ArrayList<>(fileContents);
        this.contents = new ArrayList<>();

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

    public String getHtmlPage(int pageNbr) {
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
    }

    private String noLineBreakAfterEmptyPage(String pageContent) {
        return pageContent.isEmpty() ? pageContent : pageContent + HTML_LINE_BREAK;
    }

    private boolean firstPageOfEmptyContentIsRequested(int pageNbr) {
        return contents.isEmpty() && pageNbr == 0;
    }
}
