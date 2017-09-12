package de.itemis.mosig.ringbuffer;

import java.util.ArrayList;
import java.util.List;

public class CircularBuffer {

	private int size;

	private final List<Integer> content = new ArrayList<>();

	public CircularBuffer(int size) {
		this.size = size;
	}

	public int count() {
		return content.size();
	}

	public int size() {
		return size;
	}

	public void add(int value) {
		if (!(count() < size) && !content.isEmpty()) {
			content.remove(0);
		}

		if (size > 0) {
			content.add(value);
		}
	}

	public Integer take() {
		if (count() > 0) {
			return content.remove(0);
		}
		return null;
	}

}
