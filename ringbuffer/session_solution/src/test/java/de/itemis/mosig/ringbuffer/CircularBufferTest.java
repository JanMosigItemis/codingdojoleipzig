package de.itemis.mosig.ringbuffer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class CircularBufferTest {

	@Test
	public void countShouldReturnZeroIfBufferIsEmpty() {
		assertEquals(new CircularBuffer(23).count(), 0);
	}

	@Test
	public void sizeForBufferWithSize3ShouldEqual3() {
		CircularBuffer test = new CircularBuffer(3);
		assertEquals(test.size(), 3);
	}

	@Test
	public void sizeForBufferWithSize4ShouldBe4() {
		CircularBuffer test = new CircularBuffer(4);
		assertEquals(test.size(), 4);
	}

	@Test
	public void countShouldReturnOneAfterAdd() {
		CircularBuffer circularBuffer = new CircularBuffer(1);
		circularBuffer.add(1);
		assertEquals(circularBuffer.count(), 1);
	}

	@Test
	public void countWithBiggerThanOneInAdd() {
		CircularBuffer circularBuffer = new CircularBuffer(1);
		circularBuffer.add(3);
		assertEquals(circularBuffer.count(), 1);
	}

	@Test
	public void countAfterTwoAddShouldReturnTwo() {
		CircularBuffer circularBuffer = new CircularBuffer(2);
		circularBuffer.add(3);
		circularBuffer.add(3);
		assertEquals(2, circularBuffer.count());
	}

	@Test
	public void countAfterAddingThreeElementsToBufferOfSizeTwoShouldReturnTwo() throws Exception {
		CircularBuffer circularBuffer = new CircularBuffer(2);
		circularBuffer.add(3);
		circularBuffer.add(3);
		circularBuffer.add(3);
		assertEquals(2, circularBuffer.count());
	}

	@Test
	public void takeShouldReturnThreeAfterAddThree() {
		CircularBuffer buffer = new CircularBuffer(1);
		buffer.add(3);
		assertEquals(Integer.valueOf(3), buffer.take());
	}

	@Test
	public void countShouldBeZeroAfterAddAndTake() {
		CircularBuffer buffer = new CircularBuffer(1);
		buffer.add(3);
		buffer.take();
		assertEquals(0, buffer.count());
	}

	@Test
	public void addingTwiceAndTakingOnceShouldYieldCoundOfOne() {
		CircularBuffer buffer = new CircularBuffer(2);
		buffer.add(3);
		buffer.add(3);
		buffer.take();
		assertEquals(1, buffer.count());
	}

	@Test
	public void takingTwiceDespiteOnlyHavingAddedOnceShouldYieldCountOfZero() {
		CircularBuffer buffer = new CircularBuffer(1);
		buffer.add(3);
		buffer.take();
		buffer.take();
		assertEquals(0, buffer.count());
	}

	@Test
	public void takingFromEmptyShouldYieldNull() {
		CircularBuffer buffer = new CircularBuffer(1);
		Integer result = buffer.take();
		assertNull(result);
	}

	@Test
	public void takeShouldYieldAddedValue() {
		CircularBuffer buffer = new CircularBuffer(1);
		buffer.add(7);
		Integer result = buffer.take();
		assertEquals(Integer.valueOf(7), result);
	}

	@Test
	public void takeShouldYieldFirstAddedValueAfterAddingTwo() {
		CircularBuffer buffer = new CircularBuffer(3);
		buffer.add(7);
		buffer.add(33);
		Integer result = buffer.take();
		assertEquals(Integer.valueOf(7), result);
	}

	@Test
	public void takeShouldYieldSecondAddedValueAfterAddingTwoAndTakingTwo() {
		CircularBuffer buffer = new CircularBuffer(3);
		buffer.add(7);
		buffer.add(33);
		buffer.take();
		Integer result = buffer.take();
		assertEquals(Integer.valueOf(33), result);
	}

	@Test
	public void addingToFullBufShouldReplaceOldestElem() {
		CircularBuffer buffer = new CircularBuffer(1);
		buffer.add(1);
		buffer.add(2);
		Integer result = buffer.take();
		assertEquals(Integer.valueOf(2), result);
	}

	@Test
	public void addingToFullBufShouldReplaceOldestElemX() {
		CircularBuffer buffer = new CircularBuffer(0);
		buffer.add(1);
		buffer.add(2);
		buffer.add(3);
		Integer result = buffer.take();
		assertNull(result);
	}
}
