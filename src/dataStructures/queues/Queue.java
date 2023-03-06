package dataStructures.queues;

import dataStructures.lists.LinkedList;

public class Queue<T> {
	
    private LinkedList<T> elements;
    

    public Queue() {
        this.initialize();
    }   
    
    private void initialize() {
    	this.elements = new LinkedList<>();
    }
    
    public void enqueue(T element) {
        this.elements.addLast(element);
    }

    public T dequeue() {
    	T element = this.elements.getFirst();
        this.elements.removeFirst();
        return element;
    }

    public T peek() {
        return this.elements.getFirst();
    }

    public boolean isEmpty() {
        return this.elements.getSize() == 0;
    }

    public int size() {
        return this.elements.getSize();
    }
}
