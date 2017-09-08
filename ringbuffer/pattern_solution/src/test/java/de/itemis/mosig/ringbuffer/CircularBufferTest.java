package de.itemis.mosig.ringbuffer;

import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CircularBufferTest {

	private static final int TEST_SIZE = 3;

	private CircularBuffer underTest;

	@Before
	public void setUp() {
		underTest = createTestBuffer(TEST_SIZE);
	}

	@Test
	public void whenPassingRandomNbrToTheConstructorSizeShouldBeThatNbr() {
		int expectedSize = generateRandomIntInRangeZeroTo1000();
		CircularBuffer localUnderTest = createTestBuffer(expectedSize);
		Assert.assertEquals(expectedSize, localUnderTest.size());
	}

	@Test
	public void countShouldReturnZeroIfBufferIsEmpty() {
		assertCount(0);
	}

	@Test
	public void countShouldBeOneWhenAddingAnElementToAnEmptyBuffer() {
		underTest.add(1);
		assertCount(1);
	}

	@Test
	public void takeShouldReturnNullOnAnEmptyBuffer() {
		Integer result = underTest.take();
		Assert.assertNull(result);
	}

	@Test
	public void takeShouldReturnElementOnAOneElementBuffer() {
		underTest.add(1);
		assertTake(1);
	}

	@Test
	public void takeShouldReturnFirstElementOnATwoElementBuffer() {
		underTest.add(1);
		underTest.add(2);
		assertTake(1);
	}

	@Test
	public void countShouldBeOneWhenAddingTwoElementsAndTakingOne() {
		underTest.add(1);
		underTest.add(2);
		underTest.take();
		assertCount(1);
	}

	@Test
	public void firstElementMustBe2WhenAdding1And2AndTaking1AndAdding3() {
		underTest.add(1);
		underTest.add(2);
		underTest.take();
		underTest.add(3);
		assertTake(2);
	}

	@Test
	public void addingSizePlusOneElementsOverwritesOldestElement() {
		underTest.add(1);
		underTest.add(2);
		underTest.add(3);
		underTest.add(4);
		assertTake(2);
	}

	/*
	 * ############## private helper ##############
	 */

	private void assertCount(int expectedResult) {
		Assert.assertEquals(expectedResult, underTest.count());
	}

	private void assertTake(int expectedResult) {
		Integer result = underTest.take();
		Assert.assertEquals(new Integer(expectedResult), result);
	}

	private CircularBuffer createTestBuffer(int expectedSize) {
		return new CircularBuffer(expectedSize);
	}

	private int generateRandomIntInRangeZeroTo1000() {
		int resultRangeUpperBound = 1000;
		return new Random().nextInt(resultRangeUpperBound);
	}
}
