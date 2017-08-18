package de.itemis.mosig.dojo;

import org.junit.Assert;
import org.junit.Test;

import de.itemis.mosig.dojo.RomanConverter;

public class RomanConverterTest {

	@Test
	public void shoudReturnIfor1() {
		assertExpectedResults(1, "I");
	}

	@Test
	public void shoudReturnIIfor2() {
		assertExpectedResults(2, "II");
	}

	@Test
	public void shoudReturnIIIfor3() {
		assertExpectedResults(3, "III");
	}

	@Test
	public void shoudReturnVfor5() {
		assertExpectedResults(5, "V");
	}
	
	@Test
	public void shoudReturnVIfor6() {
		assertExpectedResults(6, "VI");
	}
	
	@Test
	public void shoudReturnVIIfor7() {
		assertExpectedResults(7, "VII");
	}
	
	@Test
	public void shoudReturnXVfor15() {
		assertExpectedResults(15, "XV");
	}
	
	
	
	@Test
	public void shoudReturnXfor10() {
		assertExpectedResults(10, "X");
	}
	
	@Test
	public void shoudReturnLfor50() {
		assertExpectedResults(50, "L");
	}
	
	@Test
	public void shoudReturnCfor100() {
		assertExpectedResults(100, "C");
	}
	

	@Test
	public void shoudReturnCLfor150() {
		assertExpectedResults(150, "CL");
	}
	
	@Test
	public void shoudReturnDfor500() {
		assertExpectedResults(500, "D");
	}
	

	@Test
	public void shoudReturnDCfor600() {
		assertExpectedResults(600, "DC");
	}
	
	@Test
	public void shoudReturnMfor1000() {
		assertExpectedResults(1000, "M");
	}
	
	@Test
	public void shoudReturnMfor10000() {		
		Long time_span = assertExpectedResults(10000, "MMMMMMMMMM");
		Assert.assertTrue(time_span < 10000);
	}
	

	private Long assertExpectedResults(int input, String output) {
		Long time_start = System.currentTimeMillis();
		String result = RomanConverter.convert(input);
		Assert.assertEquals(output, result);
		Long time_end = System.currentTimeMillis();
		long time_span = time_end - time_start;
		return time_span;
	}

}
