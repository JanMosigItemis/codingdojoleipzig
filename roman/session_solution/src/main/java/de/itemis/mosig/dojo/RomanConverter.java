package de.itemis.mosig.dojo;

public class RomanConverter {

	public static String convert(int input) {
		String solution = "";
		for (int i = 0; i < input; i++) {
			solution += "I";
		}
		solution = solution.replaceAll("IIIII", "V");
		solution = solution.replaceAll("VV", "X");
		solution = solution.replaceAll("XXXXX", "L");
		solution = solution.replaceAll("LL", "C");
		solution = solution.replaceAll("CCCCC", "D");
		solution = solution.replaceAll("DD", "M");
		return solution;
	}

	
}
