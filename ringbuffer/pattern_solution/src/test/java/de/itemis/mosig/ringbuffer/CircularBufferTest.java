package de.itemis.mosig.ringbuffer;

import org.junit.Assert;
import org.junit.Test;

public class CircularBufferTest {

	@Test
	public void constructorShouldSetProvidedSize() {
		CircularBuffer underTest = new CircularBuffer(3);
		Assert.assertEquals(3, underTest.size());
	}
}
