package de.itemis.mosig.ringbuffer;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class CircularBufferTest {

	private static final int TEST_SIZE = 3;

	@Test
	public void whenPassingRandomNbrToTheConstructorSizeShouldBeThatNbr() {
		int expectedSize = generateRandomIntInRangeZeroToMax();
		CircularBuffer underTest = createTestBuffer(expectedSize);
		Assert.assertEquals(expectedSize, underTest.size());
	}

	@Test
	public void countShouldReturnZeroIfBufferIsEmpty() {
		CircularBuffer underTest = createTestBuffer(TEST_SIZE);
		Assert.assertEquals(0, underTest.count());
	}

	@Test
	public void countShouldBeOneWhenAddingAnElementToAnEmptyBuffer() {
		CircularBuffer underTest = createTestBuffer(TEST_SIZE);
		underTest.add(1);
		Assert.assertEquals(1, underTest.count());
	}

	@Test
	public void takeShouldReturnNullOnAnEmptyBuffer() {
		CircularBuffer underTest = createTestBuffer(TEST_SIZE);
		underTest.take();
	}

	/*
	 * ############## private helper ##############
	 */
	private CircularBuffer createTestBuffer(int expectedSize) {
		return new CircularBuffer(expectedSize);
	}

	private int generateRandomIntInRangeZeroToMax() {
		return new Random().nextInt(Integer.MAX_VALUE);
	}
}
