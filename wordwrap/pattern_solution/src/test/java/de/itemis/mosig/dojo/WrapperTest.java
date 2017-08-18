package de.itemis.mosig.dojo;

import org.junit.Assert;
import org.junit.Test;

public class WrapperTest {

	@Test
	public void wrapWordShorterThanCol() {
		String result = Wrapper.wrap("word", 6);

		Assert.assertEquals("word", result);
	}

	@Test
	public void wrapLongWord() {
		String result = Wrapper.wrap("123456789", 6);

		Assert.assertEquals("123456\n789", result);
	}

	@Test
	public void wrapLongWords() {
		String result = Wrapper.wrap("123456789", 3);

		Assert.assertEquals("123\n456\n789", result);
	}

	@Test
	public void wrapLongWordsWithSpaces() {
		String result = Wrapper.wrap("word word word", 6);

		Assert.assertEquals("word\nword\nword", result);
	}

	@Test
	public void wrapLongWordsWithSpaces2() {
		String result = Wrapper.wrap("123456789 word word", 6);

		Assert.assertEquals("123456\n789\nword\nword", result);
	}

	@Test
	public void wrapLongWordsWithSpaces3() {
		String result = Wrapper.wrap("word word", 4);

		Assert.assertEquals("word\nword", result);
	}
}
