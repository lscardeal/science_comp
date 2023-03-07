package dataStructures.trees;

import dataStructures.nodes.BinaryNode;

public class SearchBinaryTree<T extends Comparable<T>> {

	private BinaryNode<T> root;
	
	public void insert(T value) {
		BinaryNode<T> node = new BinaryNode<T>(value);
		
        if (root == null) {
        	insertRoot(node);
        } else {
        	insertLeaf(root, node);
        }
    }

	private BinaryNode<T> insertLeaf(BinaryNode<T> current, BinaryNode<T> node) {
		if (current == null) {
	        return node;
	    }
		
		int comparison = node.getValue().compareTo(current.getValue());
		
		if (comparison < 0) {
            current.setLeft(insertLeaf(current.getLeft(), node));
        } else if (comparison > 0) {
        	current.setRight(insertLeaf(current.getRight(), node));
        }
		
		return current;
	}

	private void insertRoot(BinaryNode<T> node) {
		root = node;
	}
	
	private void rotateLeft(BinaryNode<T> node) {
	    BinaryNode<T> pivot = node.getRight();
	    node.setRight(pivot.getLeft());
	    pivot.setLeft(node);
	    if (node == root) {
	        root = pivot;
	    }
	}

	private void rotateRight(BinaryNode<T> node) {
	    BinaryNode<T> pivot = node.getLeft();
	    node.setLeft(pivot.getRight());
	    pivot.setRight(node);
	    if (node == root) {
	        root = pivot;
	    }
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
