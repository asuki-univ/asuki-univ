package adt.datastruct;

public abstract class AbstractHashTable<K, V> {
    public abstract int size();
    public abstract void insert(K key, V value);
    public abstract V find(K key);
    public abstract void remove(K key);    
}
