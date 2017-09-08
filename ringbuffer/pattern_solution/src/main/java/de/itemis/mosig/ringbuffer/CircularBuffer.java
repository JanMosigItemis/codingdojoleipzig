package de.itemis.mosig.ringbuffer;

import java.util.Arrays;

public class CircularBuffer {

	private final int size;
	private int count = 0;
	private Integer[] elements = null;

	public CircularBuffer(int size) {
		this.size = size;
		this.elements = new Integer[size];
	}

	public void add(int element) {
		if (count < size) {
			this.elements[count] = element;
			count++;
		} else {
			take();
			add(element);
		}
	}

	public Integer take() {
		count--;
		Integer result = elements[0];
		elements = Arrays.copyOfRange(elements, 1, size + 1);

		return result;
	}

	public int size() {
		return size;
	}

	public int count() {
		return count;
	}
}
