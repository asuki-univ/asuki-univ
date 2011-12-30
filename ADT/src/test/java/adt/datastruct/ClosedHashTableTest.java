package adt.datastruct;

import adt.datastruct.impl.ClosedHashTable;


public class ClosedHashTableTest extends AbstractHashTableTest {
    public ClosedHashTableTest() {
        super(new ClosedHashTable<Integer, Integer>());
    }

}
