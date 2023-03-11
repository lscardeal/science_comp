package dataStructures.trees;

import dataStructures.nodes.BinaryNode;

public class SearchBinaryTree<T extends Comparable<T>> {

	private BinaryNode<T> root;

	private short insertions;

	private static final short INSERTIONS_TO_BALANCE = 10;

	public SearchBinaryTree(T value) {
		BinaryNode<T> node = new BinaryNode<T>(value);
		this.root = node;
		this.insertions = 0;
	}
	
	public void insert(T value) {
		BinaryNode<T> node = new BinaryNode<T>(value);
		insertHandler(root, node);
		if (insertions == INSERTIONS_TO_BALANCE) {
			balance();
			insertions = 0;
		}
    }

	private void insertHandler(BinaryNode<T> current, BinaryNode<T> node) {
		int comparison = node.getValue().compareTo(current.getValue());
		
		if (comparison < 0) {
			if (!current.hasLeft()) {
				current.setLeft(node);
				insertions++;
			} else {
				insertHandler(current.getLeft(), node);
			}
        } else if (comparison > 0) {
			if (!current.hasRight()) {
				current.setRight(node);
				insertions++;
			} else {
				insertHandler(current.getRight(), node);
			}
        }
	}
	
	private void rotateLeft(BinaryNode<T> node) {
	    BinaryNode<T> pivot = node.getRight();
		if (pivot != null) {
			node.setRight(pivot.getLeft());
			pivot.setLeft(node);
			if (node == root) {
				root = pivot;
			}
		}
	}

	private void rotateRight(BinaryNode<T> node) {
	    BinaryNode<T> pivot = node.getLeft();
		if (pivot != null) {
			node.setLeft(pivot.getRight());
			pivot.setRight(node);
			if (node == root) {
				root = pivot;
			}
		}
	}
	
	public int height() {
		return height(this.root);
	}

	private int height(BinaryNode<T> node) {
	    if (node == null) {
	        return -1;
	    }
	    return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
	}
	
	private void balance() {
	    root = balanceHandler(root);
	}

	private BinaryNode<T> balanceHandler(BinaryNode<T> node) {
		if (node == null) {
			return null;
		}

		node.setLeft(balanceHandler(node.getLeft()));
		node.setRight(balanceHandler(node.getRight()));

		int leftHeight = height(node.getLeft());
		int rightHeight = height(node.getRight());

		if (leftHeight - rightHeight > 1) {
			if (height(node.getLeft().getLeft()) < height(node.getLeft().getRight())) {
				rotateLeft(node.getLeft());
			}
			rotateRight(node);
		}

		if (rightHeight - leftHeight > 1) {
			if (height(node.getRight().getRight()) < height(node.getRight().getLeft())) {
				rotateRight(node.getRight());
			}
			rotateLeft(node);
		}
		
		return node;
	}
}
