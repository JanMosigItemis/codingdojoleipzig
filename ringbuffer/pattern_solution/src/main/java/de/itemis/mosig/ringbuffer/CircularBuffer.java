package de.itemis.mosig.ringbuffer;

public class CircularBuffer {

	private final int size;
	private int count = 0;
	private Integer[] elements = null;

	public CircularBuffer(int size) {
		this.size = size;
		this.elements = new Integer[size];
	}

	public void add(int element) {
		this.elements[count] = element;
		count++;
	}

	public Integer take() {
		return elements[0];
	}

	public int size() {
		return size;
	}

	public int count() {
		return count;
	}
}
