package dataStructures.hashTable;

import dataStructures.lists.ArrayList;
import dataStructures.nodes.HashNode;
import dataStructures.nodes.LinkedNode;

public class HashTable<K, V> {
	
	private ArrayList<LinkedNode<HashNode<K, V>>> table;
	private int size;
	
	public HashTable(int initialSize) {
		this.table = new ArrayList<>(initialSize);
		for (int i = 0; i < initialSize; i++) {
			this.table.add(null);
		}
		this.size = 0;
	}
	
	public void put(K key, V value) {
        int hash = key.hashCode();
        int index = hash % this.table.size();
        
        LinkedNode<HashNode<K, V>> node = this.table.get(index);
        while (node != null) {
            if (node.getData().getKey().equals(key)) {
                node.getData().setValue(value);
                return;
            }
            node = node.getNext();
        }
        
        HashNode<K, V> newNode = new HashNode<>(key, value);
        LinkedNode<HashNode<K, V>> newListNode = new LinkedNode<>(newNode);
        newListNode.setNext(this.table.get(index));
        this.table.set(index, newListNode);
        this.size++;
    }

    public V get(K key) {
    	int hash = key.hashCode();
        int index = hash % this.table.size();
        
        LinkedNode<HashNode<K, V>> node = this.table.get(index);
        while (node != null) {
            if (node.getData().getKey().equals(key)) {
                return node.getData().getValue();
            }
            node = node.getNext();
        }
        
        return null;
    }

    public void remove(K key) {
    	int hash = key.hashCode();
        int index = hash % this.table.size();
        
        LinkedNode<HashNode<K, V>> node = this.table.get(index);
        LinkedNode<HashNode<K, V>> prev = null;
        while (node != null) {
            if (node.getData().getKey().equals(key)) {
                if (prev == null) {
                    this.table.set(index, node.getNext());
                } else {
                    prev.setNext(node.getNext());
                }
                this.size--;
            } else {
                prev = node;
            }
            node = node.getNext();
        }
    }
    
    public int size() {
    	return this.size;
    }

}
