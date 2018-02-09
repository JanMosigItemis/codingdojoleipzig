package de.itemis.mosig.racecar.textconv;

import com.google.common.collect.Streams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class HtmlTextConverter {
    public HtmlTextConverter() {

    }

    public String convertToHtml(String input) {

        return StringEscapeUtils.escapeHtml(input);
    }
}
