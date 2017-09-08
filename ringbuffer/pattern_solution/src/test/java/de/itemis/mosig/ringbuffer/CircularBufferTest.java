package de.itemis.mosig.ringbuffer;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class CircularBufferTest {

	@Test
	public void whenPassingRandomNbrToTheConstructorSizeShouldBeThatNbr() {
		int expectedSize = new Random().nextInt(Integer.MAX_VALUE);
		CircularBuffer underTest = new CircularBuffer(expectedSize);
		Assert.assertEquals(expectedSize, underTest.size());
	}
}
