package dataStructure.lists;

import java.util.Arrays;

public class ArrayList<T> {

	private T[] elements;

	private int size;

	private static final int DEFAULT_LENGTH = 10;

	public ArrayList() {
		this.initialize();
	}

	private ArrayList(T[] elements, int size) {
		this.elements = elements;
		this.size = size;
	}

	private void initialize() {
		this.elements = (T[]) new Object[DEFAULT_LENGTH];
		this.size = 0;
	}

	public void add(T element) {
		this.resizeIfNeeded();
		this.elements[this.size++] = element;
	}

	public void remove(int index) throws IndexOutOfBoundsException {
		this.checkIndex(index);

		for (int i = index; i < this.size - 1; i++) {
			this.elements[i] = this.elements[i + 1];
		}

		elements[--this.size] = null;
	}

	public T get(int index) {
		this.checkIndex(index);
		return this.elements[index];
	}

	public int size() {
		return this.size;
	}

	public void clear() {
		this.initialize();
	}

	public ArrayList<T> clone() {
		return new ArrayList<T>(this.elements, this.size);
	}

	private boolean isFull() {
		return this.size == this.elements.length;
	}
	
	private boolean isQuarterEmpty() {
    	return this.size == this.elements.length / 4;
    }

	private void resize() {
		this.elements = Arrays.copyOf(this.elements, this.elements.length * 2);
	}

	private void resizeIfNeeded() {
		if (this.isFull() || isQuarterEmpty())
			this.resize();
	}

	private void checkIndex(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException(index);
		}
	}
}
