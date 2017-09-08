package de.itemis.mosig.ringbuffer;

public class CircularBuffer {

	private final int size;
	private int count = 0;

	public CircularBuffer(int size) {
		this.size = size;
	}

	public void add(int element) {
		count++;
	}

	public Integer take() {
		return null;
	}

	public int size() {
		return size;
	}

	public int count() {
		return count;
	}
}
