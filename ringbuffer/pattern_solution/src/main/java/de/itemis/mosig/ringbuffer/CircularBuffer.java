package de.itemis.mosig.ringbuffer;

import java.util.Arrays;

import com.google.common.base.Preconditions;

public class CircularBuffer {

	private final int size;

	private int count = 0;
	private Integer[] elements = null;

	public CircularBuffer(int size) {
		Preconditions.checkArgument(size != 0);

		this.size = size;
		this.elements = new Integer[size];
	}

	public void add(int element) {
		if (bufferIsFull()) {
			take();
			add(element);
		} else {
			this.elements[count] = element;
			count++;
		}
	}

	public Integer take() {
		count--;
		Integer result = elements[0];
		shiftLeftByOneElement();

		return result;
	}

	public int size() {
		return size;
	}

	public int count() {
		return count;
	}

	private boolean bufferIsFull() {
		return count == size;
	}

	private void shiftLeftByOneElement() {
		elements = Arrays.copyOfRange(elements, 1, size + 1);
	}
}
