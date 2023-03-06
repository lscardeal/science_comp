package dataStructures.stacks;

import java.util.Arrays;

public class Stack<T> {

    private static final int DEFAULT_LENGTH = 10;

    private T[] elements;
    private int size;

    public Stack() {
        this.initialize();
    }   
    
    private void initialize() {
    	this.elements = (T[]) new Object[DEFAULT_LENGTH];
    	this.size = 0;
    }
    
    public void push(T element) {
        this.resizeIfNeeded();
        this.elements[this.size++] = element;
    }

    public T pop() {
    	this.checkEmpty();
        
        T element = this.elements[--this.size];
        this.elements[this.size] = null;
        this.resizeIfNeeded();
        
        return element;
    }

    public T peek() {
    	this.checkEmpty();
        return this.elements[this.size - 1];
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    private boolean isFull() {
		return this.size == this.elements.length;
	}
    
    private boolean isQuarterEmpty() {
    	return this.size == this.elements.length / 4;
    }
    
	private void resize() {
		this.elements = Arrays.copyOf(this.elements, this.size * 2);
	}

	private void resizeIfNeeded() {
		if (this.isFull() || this.isQuarterEmpty())
			this.resize();
	}
	
	private void checkEmpty() {
		if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
	}
}
