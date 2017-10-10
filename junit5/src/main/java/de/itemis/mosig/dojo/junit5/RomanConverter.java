package de.itemis.mosig.dojo.junit5;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

public class RomanConverter {

	private static final List<Integer> NUMBERS_ORDERED_DESC = Lists.newArrayList(1000, 500, 100, 50, 10, 5, 1);
	private static final Map<Integer, String> NUMBER_MAPPING = ImmutableMap.<Integer, String>builder().put(1, "I")
			.put(5, "V").put(10, "X").put(50, "L").put(100, "C").put(500, "D").put(1000, "M").build();

	public String convert(int input) {
		String result = "";
		if (input > 0) {
			int subtractor = highestNumberThatCanBeSubtractedFromInputWithoutForItToBecomeNegative(input);
			result = NUMBER_MAPPING.get(subtractor) + convert(input - subtractor);
		}
		return result;
	}

	private int highestNumberThatCanBeSubtractedFromInputWithoutForItToBecomeNegative(int input) {
		int candidate = 0;
		for (int candidateIndex = 0; candidateIndex < NUMBERS_ORDERED_DESC.size(); candidateIndex++) {
			candidate = NUMBERS_ORDERED_DESC.get(candidateIndex);
			if (input - candidate >= 0) {
				break;
			}
		}

		return candidate;
	}
}
