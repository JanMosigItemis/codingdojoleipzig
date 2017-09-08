package de.itemis.mosig.ringbuffer;

public class CircularBuffer {

	private final int size;
	private int count = 0;
	private Integer element = null;

	public CircularBuffer(int size) {
		this.size = size;
	}

	public void add(int element) {
		this.element = element;
		count++;
	}

	public Integer take() {
		return this.element;
	}

	public int size() {
		return size;
	}

	public int count() {
		return count;
	}
}
