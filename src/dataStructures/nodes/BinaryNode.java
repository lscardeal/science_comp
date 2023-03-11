package dataStructures.nodes;

public class BinaryNode<T extends Comparable<T>> {

	private T value;
	
	private BinaryNode<T> left;
	
	private BinaryNode<T> right;
	
	public BinaryNode(T value) {
		this.value = value;
	}
	
	public T getValue() {
		return this.value;
	}
	
	public BinaryNode<T> getLeft() {
		return this.left;
	}
	
	public BinaryNode<T> getRight() {
		return this.right;
	}
	
	public void setLeft(BinaryNode<T> node) {
		this.left = node;
	}
	
	public void setRight(BinaryNode<T> node) {
		this.right = node;
	}

	public boolean isFull() {
		return left != null && right != null;
	}

	public boolean isEmpty() {
		return left == null && right == null;
	}

	public boolean hasLeft() {
		return this.left != null;
	}

	public boolean hasRight() {
		return this.right != null;
	}
}
