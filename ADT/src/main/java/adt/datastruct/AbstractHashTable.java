package adt.datastruct;

public abstract class AbstractHashTable<K, V> {

    static class Entry<K, V> {
        public K key;
        public V value;
        
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public abstract int size();
    public abstract void insert(K key, V value);
    public abstract V find(K key);
    public abstract void remove(K key);    
}
