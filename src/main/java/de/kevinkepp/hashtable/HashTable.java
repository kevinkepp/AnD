package de.kevinkepp.hashtable;

public interface HashTable<K,V> {

	V get(K key);

	void put(K key, V value);

	boolean contains(V value);

	V remove(K key);

	boolean isEmpty();
}
