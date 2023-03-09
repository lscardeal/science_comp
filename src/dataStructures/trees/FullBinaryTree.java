package dataStructures.trees;

import dataStructures.nodes.BinaryNode;

public class FullBinaryTree<T extends Comparable<T>> {
    
    private BinaryNode<T> root;
    
    public FullBinaryTree(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value can't be null");
        }

        root = new BinaryNode<T>(value);
    }
    
    public FullBinaryTree(T value, FullBinaryTree<T> left, FullBinaryTree<T> right) {
        if (value == null && left != null && right != null) {
            throw new IllegalArgumentException("Value can't be null");
        }

        root = new BinaryNode<T>(value);
        root.setLeft(left.root);
        root.setRight(right.root);
    }
    
    private boolean isLeaf(BinaryNode<T> node) {
        return node != null && node.getLeft() == null && node.getRight() == null;
    }
    
    public boolean isFull() {
        if (root.isEmpty()) {
            return true;
        }
        return isFullHandler(root);
    }
    
    private boolean isFullHandler(BinaryNode<T> node) {
        if (node == null) {
            return true;
        }
        if (isLeaf(node)) {
            return true;
        }

        if (node.getLeft() != null && node.getRight() != null) {
            return isFullHandler(node.getLeft()) && isFullHandler(node.getRight());
        }

        return false;
    }
    
    public int height() {
        return heightHandler(root);
    }

    private int heightHandler(BinaryNode<T> node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = heightHandler(node.getLeft());
        int rightHeight = heightHandler(node.getRight());
        return 1 + Math.max(leftHeight, rightHeight);
    }
    
    private BinaryNode<T> getLowestLeaf(BinaryNode<T> node) {
    	if (node.getLeft() == null) {
    		return node;
    	}
    	
    	int leftHeight = heightHandler(node.getLeft());
    	int rightHeight = heightHandler(node.getRight());
    	
    	BinaryNode<T> resultNode;
    	if (leftHeight > rightHeight) {
    		resultNode = getLowestLeaf(node.getRight());
    	} else {
    		resultNode = getLowestLeaf(node.getLeft());
    	}
    	
    	return resultNode;
    }
    
    public void add(T leftValue, T rightValue) {
    	BinaryNode<T> node = getLowestLeaf(this.root);
    	BinaryNode<T> leftNode = new BinaryNode<T>(leftValue);
    	BinaryNode<T> rightNode = new BinaryNode<T>(rightValue);
    	node.setLeft(leftNode);
    	node.setRight(rightNode);
    }
}
