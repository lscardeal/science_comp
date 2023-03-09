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
    	if (!node.isFull()) {
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

    private int heightHandler(BinaryNode<T> node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = heightHandler(node.getLeft());
        int rightHeight = heightHandler(node.getRight());
        return 1 + Math.min(leftHeight, rightHeight);
    }
}
