package adt.datastruct;

import adt.datastruct.impl.OpenHashTable;


public class OpenHashTableTest extends AbstractHashTableTest {
    public OpenHashTableTest() {
        super(new OpenHashTable<Integer, Integer>());
    }
}
