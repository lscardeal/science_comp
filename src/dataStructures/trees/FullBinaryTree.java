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
        return isFullHelper(root);
    }
    
    private boolean isFullHelper(BinaryNode<T> node) {
        if (node == null) {
            return true;
        }
        if (isLeaf(node)) {
            return true;
        }

        if (node.getLeft() != null && node.getRight() != null) {
            return isFullHelper(node.getLeft()) && isFullHelper(node.getRight());
        }

        return false;
    }
    
    public int height() {
        return heightHelper(root);
    }

    private int heightHelper(BinaryNode<T> node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = heightHelper(node.getLeft());
        int rightHeight = heightHelper(node.getRight());
        return 1 + Math.max(leftHeight, rightHeight);
    }
    
    private BinaryNode<T> getLowestLeaf(BinaryNode<T> node) {
    	if (!node.isFull()) return node;
    	
    	int leftHeight = heightHelper(node.getLeft());
    	int rightHeight = heightHelper(node.getRight());
    	
    	return leftHeight > rightHeight ?
            getLowestLeaf(node.getRight()) :
            getLowestLeaf(node.getRight());
    }
    
    public void add(T leftValue, T rightValue) {
    	BinaryNode<T> node = getLowestLeaf(this.root);
    	BinaryNode<T> leftNode = new BinaryNode<T>(leftValue);
    	BinaryNode<T> rightNode = new BinaryNode<T>(rightValue);
    	node.setLeft(leftNode);
    	node.setRight(rightNode);
    }
}
