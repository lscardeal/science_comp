package dataStructures.trees;

import dataStructures.nodes.BinaryNode;

public class CompleteBinaryTree<T extends Comparable<T>> {

    private BinaryNode<T> root;

    public CompleteBinaryTree(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value can't be null");
        }

        root = new BinaryNode<T>(value);
    }

    public void add(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value can't be null");
        }
        
    	BinaryNode<T> lowestNode = getLowestLeaf(this.root);
    	BinaryNode<T> newNode = new BinaryNode<T>(value);
    	
        if (lowestNode.isEmpty()) {
            lowestNode.setLeft(newNode);
        } else if (lowestNode.isFull()) {
            lowestNode.getLeft().setLeft(newNode);
        } else {
            lowestNode.setRight(newNode);
        }
    }

    private BinaryNode<T> getLowestLeaf(BinaryNode<T> node) {
    	if (!node.isFull()) return node;
    	
    	int leftHeight = heightHelper(node.getLeft());
    	int rightHeight = heightHelper(node.getRight());
    	
    	return leftHeight > rightHeight ?
            getLowestLeaf(node.getRight()) :
            getLowestLeaf(node.getRight());
    }

    private int heightHelper(BinaryNode<T> node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = heightHelper(node.getLeft());
        int rightHeight = heightHelper(node.getRight());
        return 1 + Math.min(leftHeight, rightHeight);
    }
}
