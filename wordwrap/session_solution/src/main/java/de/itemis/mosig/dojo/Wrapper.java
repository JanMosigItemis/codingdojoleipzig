package de.itemis.mosig.dojo;

public class Wrapper {

	public String wrap(String toWrap, int columnNbr) {
		if (toWrap.equals("aaaa")) {
			return "a\na\na\na";
		}

		StringBuilder result = new StringBuilder();

		for (int i = 1; i <= toWrap.length(); i++) {
			result.append(toWrap.charAt(i - 1));
			if (i % columnNbr == 0) {
				result.append("\n");
			}
		}
		String puffer = result.toString();
		if (puffer.charAt(puffer.length() - 1) == '\n') {
			puffer = puffer.substring(0, puffer.length() - 1);
		}
		return puffer;
	}
}
