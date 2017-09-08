package de.itemis.mosig.ringbuffer;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class CircularBufferTest {

	@Test
	public void whenPassingRandomNbrToTheConstructorSizeShouldBeThatNbr() {
		int expectedSize = generateRandomIntInRangeZeroToMax();
		CircularBuffer underTest = createTestBuffer(expectedSize);
		Assert.assertEquals(expectedSize, underTest.size());
	}

	@Test
	public void countShouldReturnZeroIfBufferIsEmpty() {
		CircularBuffer underTest = createTestBuffer(3);
		Assert.assertEquals(0, underTest.count());
	}

	@Test
	public void countShouldReturnOneWhenAddingAnElementToAnEmptyBuffer() {
		CircularBuffer underTest = createTestBuffer(3);
		underTest.add();
	}

	private CircularBuffer createTestBuffer(int expectedSize) {
		return new CircularBuffer(expectedSize);
	}

	private int generateRandomIntInRangeZeroToMax() {
		return new Random().nextInt(Integer.MAX_VALUE);
	}
}
