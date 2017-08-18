package de.itemis.mosig.dojo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.itemis.mosig.dojo.RomanConverter;

public class RomanConverterTest {

	private RomanConverter underTest;

	@Before
	public void setUp() {
		this.underTest = new RomanConverter();
	}

	@Test
	public void shouldConvertOneToI() {
		assertConvertResult(1, "I");
	}

	@Test
	public void shouldConvertTwoToII() {
		assertConvertResult(2, "II");
	}

	@Test
	public void shouldConvertFiveToV() {
		assertConvertResult(5, "V");
	}

	@Test
	public void shouldConvertTenToX() {
		assertConvertResult(10, "X");
	}

	@Test
	public void shouldConvertFiftyToL() {
		assertConvertResult(50, "L");
	}

	@Test
	public void shouldConvertOneHundredToC() {
		assertConvertResult(100, "C");
	}

	@Test
	public void shouldConvertFiveHundredToD() {
		assertConvertResult(500, "D");
	}

	@Test
	public void shouldConvertOneThousandToM() {
		assertConvertResult(1000, "M");
	}

	@Test
	public void shouldConvert6ToVI() {
		assertConvertResult(6, "VI");
	}

	@Test
	public void shouldConvert16ToXVI() {
		assertConvertResult(16, "XVI");
	}

	@Test
	public void shouldConvert66ToLXVI() {
		assertConvertResult(66, "LXVI");
	}

	@Test
	public void shouldConvert166ToCLXVI() {
		assertConvertResult(166, "CLXVI");
	}

	@Test
	public void shouldConvert666ToDCLXVI() {
		assertConvertResult(666, "DCLXVI");
	}

	@Test
	public void shouldConvert1666ToMDCLXVI() {
		assertConvertResult(1666, "MDCLXVI");
	}

	@Test
	public void shouldConvert0ToNothing() {
		assertConvertResult(0, "");
	}

	private void assertConvertResult(int input, String expectedResult) {
		String result = underTest.convert(input);

		Assert.assertEquals(expectedResult, result);
	}
}
