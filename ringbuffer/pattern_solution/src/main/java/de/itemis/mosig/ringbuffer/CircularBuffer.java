package de.itemis.mosig.ringbuffer;

public class CircularBuffer {

	private final int size;

	public CircularBuffer(int size) {
		this.size = size;
	}

	public int size() {
		return size;
	}
}
