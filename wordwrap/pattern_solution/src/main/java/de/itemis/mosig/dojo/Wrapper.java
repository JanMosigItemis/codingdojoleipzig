package de.itemis.mosig.dojo;

public class Wrapper {

	public static String wrap(String input, int columnNumber) {
		int inputLength = input.length();

		if (inputLength > columnNumber) {
			int spaceWrap = input.substring(0, columnNumber).lastIndexOf(" ");
			spaceWrap = spaceWrap == -1 ? columnNumber : spaceWrap;
			String tail = input.substring(spaceWrap).trim();
			String suffix = wrap(tail, columnNumber);
			return input.substring(0, spaceWrap) + "\n" + suffix;
		} else {
			return input;
		}
	}
}
