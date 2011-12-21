package adt.datastruct;


public class ClosedHashTable<K, V> extends AbstractHashTable<K, V> {
    private int size = 0;
    private Entry<K, V>[] table; 

    @SuppressWarnings("unchecked")
    public ClosedHashTable() {
        this.size = 0;
        this.table = new Entry[16];
    }
    
    @Override
    public int size() {
        return size;
    }

    @Override
    public void insert(K key, V value) {
        if (key == null)
            throw new RuntimeException("Key should not be null.");

        if (size() * 2 >= table.length)
            reconstruct();        
             
        insertToTable(this.table, key, value);
    }
    
    private void insertToTable(Entry<K, V>[] table, K key, V value) {
        int index = key.hashCode() % table.length;
        while (true) {
            if (table[index] == null || table[index].key == null) {
                table[index] = new Entry<K, V>(key, value);
                ++size;
                return;
            }
            
            if (table[index].key.equals(key)) {
                table[index] = new Entry<K, V>(key, value);
                return;
            }
                        
            index = (index + 1) % table.length;
        }

    }

    @Override
    public V find(K key) {
        if (key == null)
            throw new RuntimeException("Key should not be null.");
        
        int index = key.hashCode() % table.length;
        while (true) {
            if (table[index] == null)
                return null;
            
            if (table[index].key == null) {
                index = (index + 1) % table.length;
                continue;
            }
                
            
            if (table[index].key.equals(key)) {
                return table[index].value;
            }
                        
            index = (index + 1) % table.length;
        }
    }

    @Override
    public void remove(K key) {
        if (key == null)
            throw new RuntimeException("Key should not be null.");
        
        int index = key.hashCode() % table.length;
        while (true) {
            if (table[index] == null)
                return;
            
            if (table[index].key == null)
                continue;
            
            if (table[index].key.equals(key)) {
                table[index].key = null;
                --size;
                return;
            }
                        
            index = (index + 1) % table.length;
        }
    }
    
    private void reconstruct() {
        int newSize = size() * 4;
        @SuppressWarnings("unchecked")
        Entry<K, V>[] newTable = new Entry[newSize];
        
        this.size = 0;
        for (int i = 0; i < table.length; ++i) {
            if (table[i] == null || table[i].key == null)
                continue;
            
            insertToTable(newTable, table[i].key, table[i].value);            
        }
        
        this.table = newTable;
    }
}
