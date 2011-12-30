package adt.datastruct.impl;

import java.util.Iterator;
import java.util.LinkedList;

import adt.datastruct.AbstractHashTable;
import adt.datastruct.Entry;

public class OpenHashTable<K, V> extends AbstractHashTable<K, V> {
    private int size;
    private LinkedList<Entry<K, V>>[] table;
    
    @SuppressWarnings("unchecked")
    public OpenHashTable() {
        size = 0;
        table = new LinkedList[16];                
    }
    
    public int size() {
        return size;
    }
    
    @Override
    public void insert(K key, V value) {
        if (key == null)
            throw new RuntimeException("Key should not be empty.");
        
        int index = key.hashCode() % table.length;
        insertTable(table, index, key, value);
        
        if (size() >= table.length)
            reconstruct();
    }
    
    private void insertTable(LinkedList<Entry<K, V>>[] table, int index, K key, V value) {
        if (table[index] == null)
            table[index] = new LinkedList<Entry<K, V>>();

        for (Iterator<Entry<K, V>> it = table[index].iterator(); it.hasNext(); ) {
            Entry<K, V> entry = it.next();
            if (key.equals(entry.key)) {
                entry.value = value;
                return;
            }
        }

        ++size;
        table[index].add(new Entry<K, V>(key, value));
    }

    @Override
    public V find(K key) {
        int hash = key.hashCode() % table.length;
        
        if (table[hash] == null)
            return null;
        
        for (Iterator<Entry<K, V>> it = table[hash].iterator(); it.hasNext(); ) {
            Entry<K, V> entry = it.next();
            if (key.equals(entry.key))
                return entry.value;
        }
        
        return null;
    }
    
    @Override
    public void remove(K key) {
        int hash = key.hashCode() % table.length;
        if (table[hash] == null)
            return;
        
        for (Iterator<Entry<K, V>> it = table[hash].iterator(); it.hasNext(); ) {
            Entry<K, V> entry = it.next();
            if (key.equals(entry.key)) {
                it.remove();
                --size;
                return;
            }
        }
    }
    
    private void reconstruct() {
        int newSize = table.length * 2;
        @SuppressWarnings("unchecked")
        LinkedList<Entry<K, V>>[] newTable = new LinkedList[newSize];
        this.size = 0;
        
        for (int i = 0; i < table.length; ++i) {
            if (table[i] == null)
                continue;

            for (Iterator<Entry<K, V>> it = table[i].iterator(); it.hasNext(); ) {
                Entry<K, V> entry = it.next();
                int index = entry.key.hashCode() % newSize;
                insertTable(newTable, index, entry.key, entry.value);
            }            
        }
        
        this.table = newTable; 
    }
}
