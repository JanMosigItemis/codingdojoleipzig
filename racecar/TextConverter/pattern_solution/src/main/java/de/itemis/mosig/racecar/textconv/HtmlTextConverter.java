package de.itemis.mosig.racecar.textconv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class HtmlTextConverter
{
    private String fullFilenameWithPath;

    public HtmlTextConverter() {

    }

    public String convertToHtml(String input) throws IOException{

	    String line = null;
	    String html = "&lt;&gt;";
	    while (line != null)
	    {
	    	html += StringEscapeUtils.escapeHtml(line);
	        html += "<br />";
	    }
	    return html;

    }

	public String getFilename() {
		return this.fullFilenameWithPath;
	}
}
