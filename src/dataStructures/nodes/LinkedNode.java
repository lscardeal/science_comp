package dataStructures.nodes;

public class LinkedNode<T> {

		private T value;
		private LinkedNode<T> next;

		public LinkedNode(T value) {
			this.value = value;
		}

		public T getValue() {
			return value;
		}

		public LinkedNode<T> getNext() {
			return next;
		}
		
		public void setNext(LinkedNode<T> next) {
			this.next = next;
		}
		
		public boolean hasNext() {
			return this.next != null;
		}
	}