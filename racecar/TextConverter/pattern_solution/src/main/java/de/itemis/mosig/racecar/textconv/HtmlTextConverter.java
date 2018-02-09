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

        for(int i=0;i<input.length();i++) {
            String currentChar = input.charAt(i) + "";
            switch(currentChar) {
                case "<" : result += "&lt;"; break;
                case ">" : result += "&gt;"; break;
                case "&" : result += "&amp;"; break;
                case "\"" : result += "&quot;"; break;
                default : result += currentChar;
            }
        }

	    return result;
    }
}
