package de.itemis.mosig.dojo.junit5;

// TODO remove old imports and import new junit-jupiter api
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RomanConverterTest {

	private RomanConverter underTest;

	// TODO annotate this method, thus junit-5 executes it before each test case
	public void setUp() {
		this.underTest = new RomanConverter();
	}

	// TODO make use of junit-5 Test annotation
	public void shouldConvertOneToI() {
		String result = underTest.convert(1);
		Assert.assertEquals("I", result);
	}

	// TODO specify a custom name for this test case
	public void shouldConvertTwoToII() {
		String result = underTest.convert(2);
		Assert.assertEquals("II", result);
	}

	// TODO prevent this test case to be executed
	@Test
	public void shouldConvertFiveToV() { assert(false); }

	// TODO add custom assertion message as string
	@Test
	public void shouldConvertTenToX() {
		String result = underTest.convert(10);
		Assert.assertEquals("X", result);
	}

	// TODO add custom assertion message as lambda expression
	@Test
	public void shouldConvertFiftyToL() {
		String result = underTest.convert(50);
		Assert.assertEquals("L", result);
	}

	@Test
	public void shouldConvertOneHundredToC() {
		String result = underTest.convert(100);
		Assert.assertEquals("C", result);
	}

	@Test
	public void shouldConvertFiveHundredToD() {
		String result = underTest.convert(500);
		Assert.assertEquals("D", result);
	}

	@Test
	public void shouldConvertOneThousandToM() {
		String result = underTest.convert(1000);
		Assert.assertEquals("M", result);
	}

	@Test
	public void shouldConvert6ToVI() {
		String result = underTest.convert(6);
		Assert.assertEquals("VI", result);
	}

	@Test
	public void shouldConvert16ToXVI() {
		String result = underTest.convert(16);
		Assert.assertEquals("XVI", result);
	}

	@Test
	public void shouldConvert66ToLXVI() {
		String result = underTest.convert(66);
		Assert.assertEquals("LXVI", result);
	}

	@Test
	public void shouldConvert166ToCLXVI() {
		String result = underTest.convert(166);
		Assert.assertEquals("CLXVI", result);
	}

	@Test
	public void shouldConvert666ToDCLXVI() {
		String result = underTest.convert(666);
		Assert.assertEquals("DCLXVI", result);
	}

	@Test
	public void shouldConvert1666ToMDCLXVI() {
		String result = underTest.convert(1666);
		Assert.assertEquals("MDCLXVI", result);
	}

	@Test
	public void shouldConvert0ToNothing() {
		String result = underTest.convert(0);
		Assert.assertEquals("", result);
	}

}
