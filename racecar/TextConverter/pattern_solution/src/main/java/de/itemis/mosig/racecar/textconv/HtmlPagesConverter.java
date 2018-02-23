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

        handleSpecialPageBreakCases(rawContents);
        copyLineIfItIsNoPageBreak(rawContents);
    }

    private void copyLineIfItIsNoPageBreak(List<String> rawContents) {
        for (int i = 0; i < rawContents.size(); i++) {
            String line = rawContents.get(i);
            if (line.equals(PAGE_BREAK)) {
                if (nextLineContainsPageBreak(rawContents, i)) {
                    contents.add("");
                }
            } else {
                contents.add(line);
            }
        }
    }

    private void handleSpecialPageBreakCases(List<String> rawContents) {
        if (firstLineContainsPageBreak(rawContents)) {
            rawContents.add(0, "");
        }

        if (lastLineContainsPageBreak(rawContents)) {
            rawContents.add("");
        }
    }

    public String getHtmlPage(int pageNbr) {
        if (pageNbrInRange(pageNbr)) {
            return noLineBreakAfterEmptyPage(contents.get(pageNbr));
        } else if (firstPageOfEmptyContentIsRequested(pageNbr)) {
            return "";
        }

        return null;
    }

    private boolean pageNbrInRange(int pageNbr) {
        return pageNbr > -1 && pageNbr < contents.size();
    }

    private boolean nextLineContainsPageBreak(List<String> lines, int currentLineNbr) {
        return currentLineNbr < lines.size() - 1 && lines.get(currentLineNbr + 1).equals(PAGE_BREAK);
    }

    private boolean lastLineContainsPageBreak(List<String> lines) {
        return !lines.isEmpty() && lines.get(lines.size() - 1).equals(PAGE_BREAK);
    }

    private boolean firstLineContainsPageBreak(List<String> lines) {
        return !lines.isEmpty() && lines.get(0).equals(PAGE_BREAK);
    }

    private String noLineBreakAfterEmptyPage(String pageContent) {
        return pageContent.isEmpty() ? pageContent : pageContent + HTML_LINE_BREAK;
    }

    private boolean firstPageOfEmptyContentIsRequested(int pageNbr) {
        return contents.isEmpty() && pageNbr == 0;
    }
}
