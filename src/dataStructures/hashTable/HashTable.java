package dataStructures.hashTable;

import dataStructures.lists.ArrayList;
import dataStructures.nodes.HashNode;

public class HashTable<K, V> {
	
	private ArrayList<HashNode<K, V>> table;
	
	public HashTable() {
		this.table = new ArrayList<HashNode<K, V>>();
	}
	
	public void put(K key, V value) {
        int hash = key.hashCode();
        
        for (HashNode<K, V> hashNode : table) {
			if (hashNode.getKey().hashCode() == hash) {
				hashNode.setValue(value);
				return;
			}
		}
        
        HashNode<K, V> node = new HashNode<K, V>(key, value);
        this.table.add(node);
    }

    public V get(K key) {
    	int hash = key.hashCode();
        
        for (HashNode<K, V> hashNode : table) {
			if (hashNode.getKey().hashCode() == hash) {
				return hashNode.getValue();
			}
		}
        
        return null;
    }

    public void remove(K key) {
    	int hash = key.hashCode();
        
    	for (HashNode<K, V> hashNode : table) {
			if (hashNode.getKey().hashCode() == hash) {
				this.table.remove(hashNode);
			}
		}
    }

}
