package dataStructures.lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

import dataStructures.nodes.LinkedNode;

public class LinkedList<T> implements Iterable<T> {

	private LinkedNode<T> head;

	private LinkedNode<T> tail;

	private int size;

	public LinkedList() {
		this.initialize();
	}

	private void initialize() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	public int getSize() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	private boolean hasOneNode() {
		return this.size == 1;
	}

	public void addFirst(T value) {
		LinkedNode<T> node = createNode(value);
		node.setNext(this.head);
		this.head = node;
		this.tail = node;
		this.size++;
	}

	public void addLast(T value) {
		LinkedNode<T> node = createNode(value);

		if (this.isEmpty()) {
			this.head = node;
		}

		node.setNext(this.tail);
		this.tail = node;
		this.size++;
	}

	public T get(int index) throws IndexOutOfBoundsException {
		this.checkIndex(index);

		if (index == 0) {
			return getFirst();
		} else if (index == this.size - 1) {
			return getLast();
		}

		LinkedNode<T> current = this.head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}

		return current.getValue();
	}

	public T getFirst() {
		return this.head.getValue();
	}

	public T getLast() {
		return this.tail.getValue();
	}

	public void add(int index, T value) throws IndexOutOfBoundsException {
		if (index == 0) {
			this.addFirst(value);
			return;
		} else if (index == this.size) {
			this.addLast(value);
			return;
		}

		this.checkIndex(index);
		LinkedNode<T> node = this.createNode(value);
		LinkedNode<T> current = this.head;

		for (int i = 0; i < index - 1; i++) {
			current = current.getNext();
		}

		node.setNext(current.getNext());
		current.setNext(node);
		this.size++;
	}

	public void removeFirst() throws IndexOutOfBoundsException {
		if (this.isEmpty()) {
			return;
		} else if (this.hasOneNode()) {
			this.initialize();
			return;
		}

		this.head = this.head.getNext();
		size--;
	}

	public void removeLast() throws IndexOutOfBoundsException {
		if (this.isEmpty()) {
			return;
		} else if (this.hasOneNode()) {
			this.initialize();
			return;
		}

		LinkedNode<T> current = head;
		while (current.getNext().hasNext()) {
			current = current.getNext();
		}

		current.setNext(null);
		size--;
	}

	public void remove(int index) throws IndexOutOfBoundsException {
		if (index == 0) {
			this.removeFirst();
			return;
		} else if (index == this.size - 1) {
			this.removeLast();
			return;
		}

		if (this.isEmpty()) {
			return;
		} else if (this.hasOneNode()) {
			this.initialize();
			return;
		}

		this.checkIndex(index - 1);
		LinkedNode<T> current = this.head;
		for (int i = 0; i < index - 1; i++) {
			current = current.getNext();
		}

		current.setNext(current.getNext().getNext());
		size--;
	}

	public int indexOf(T value) {
		LinkedNode<T> current = this.head;
		for (int i = 0; i < size; i++) {
			if (current.getValue().equals(value)) {
				return i;
			}
			current = current.getNext();
		}
		return -1;
	}

	public boolean contains(T value) {
		return this.indexOf(value) != -1;
	}

	public void remove(T value) {
		int index = this.indexOf(value);
		this.remove(index);
	}

	private LinkedNode<T> createNode(T value) {
		return new LinkedNode<T>(value);
	}

	private void checkIndex(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(index);
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements Iterator<T> {

		private LinkedNode<T> current;

		private LinkedListIterator() {
			current = head;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T value = current.getValue();
			current = current.getNext();
			return value;
		}
	}

}
