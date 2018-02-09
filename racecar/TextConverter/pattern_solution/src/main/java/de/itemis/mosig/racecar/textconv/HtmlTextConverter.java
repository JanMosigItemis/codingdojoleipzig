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

        String result = "";
        String firstChar = input.charAt(0)+"";

        switch(firstChar) {
            case "<" : result += "&lt;"; break;
            case ">" : result += "&gt;"; break;
            case "&" : result += "&amp;"; break;
            case "\"" : result += "&quot;"; break;
            case "'" : result += "&#39;"; break;
            default : result += firstChar;
        }

	    return result;
    }
}
