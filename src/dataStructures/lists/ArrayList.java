package dataStructures.lists;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements Iterable<T> {

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

	public void add(T value) {
		this.resizeIfNeeded();
		this.elements[this.size++] = value;
	}

	public void remove(int index) throws IndexOutOfBoundsException {
		this.checkIndex(index);

		for (int i = index; i < this.size - 1; i++) {
			this.elements[i] = this.elements[i + 1];
		}

		elements[--this.size] = null;
	}
	
	public void remove(T value) {
		int index = this.indexOf(value);
		if (index >= 0) {
			this.remove(index);
		}
	}
	
	public int indexOf(T value) {
		for (int i = 0; i < this.size - 1; i++) {
			if (this.elements.equals(value)) {
				return i;
			}
		}
		
		return -1;
	}

	public void removeFirst() {
		this.remove(0);
	}

	public void removeLast() {
		this.remove(this.size - 1);
	}

	public T get(int index) {
		this.checkIndex(index);
		return this.elements[index];
	}

	public T getFirst() {
		this.checkIndex(0);
		return this.elements[0];
	}

	public T getLast() {
		int index = this.size - 1;
		this.checkIndex(index);
		return this.elements[index];
	}

	public int getSize() {
		return this.size;
	}

	public void clear() {
		this.initialize();
	}

	public ArrayList<T> clone() {
		return new ArrayList<T>(this.elements.clone(), this.size);
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

	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}

	private class ArrayListIterator implements Iterator<T> {

		private int index;

		private ArrayListIterator() {
			index = 0;
		}

		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return elements[index++];
		}
	}
}
