package adt.datastruct;

import java.util.Random;

import junit.framework.Assert;

import org.junit.Test;

public abstract class AbstractHashTableTest {
    private AbstractHashTable<Integer, Integer> hashTable;

    protected AbstractHashTableTest(AbstractHashTable<Integer, Integer> hashTable) {
        this.hashTable = hashTable;
    }
    
    @Test
    public void testEmpty() {
        Assert.assertEquals(0, hashTable.size());
        Assert.assertNull(hashTable.find(0));
        Assert.assertNull(hashTable.find(1));
        Assert.assertNull(hashTable.find(2));
    }

    @Test
    public void testSingle() {
        hashTable.insert(1, 1);
        Assert.assertEquals(1, hashTable.size());
        Assert.assertNull(hashTable.find(0));
        Assert.assertEquals(1, (int)hashTable.find(1));
        Assert.assertNull(hashTable.find(2));        
    }

    @Test
    public void testSeveral() {
        Random random = new Random(100);
        int[] vs = new int[10];
        for (int i = 0; i < 10; ++i) 
            vs[i] = random.nextInt();

        for (int i = 0; i < 10; ++i)            
            hashTable.insert(i, i);

        Assert.assertEquals(10, hashTable.size());

        for (int i = 0; i < 10; ++i)            
            Assert.assertEquals(i, (int)hashTable.find(i));
    }

    @Test
    public void testMany() {
        Random random = new Random(100);
        int[] vs = new int[1000];
        for (int i = 0; i < 1000; ++i) 
            vs[i] = random.nextInt();

        for (int i = 0; i < 1000; ++i)            
            hashTable.insert(i, i);

        Assert.assertEquals(1000, hashTable.size());

        for (int i = 0; i < 1000; ++i)            
            Assert.assertEquals(i, (int)hashTable.find(i));
    }

    @Test
    public void testInsertAndRemove() {
        Assert.assertEquals(0, hashTable.size());
        for (int i = 0; i < 10; ++i)
            hashTable.insert(i, i);
        Assert.assertEquals(10, hashTable.size());
        for (int i = 0; i < 10; ++i)
            hashTable.remove(i);
        Assert.assertEquals(0, hashTable.size());        
    }

    @Test
    public void testInsertSame() {
        Assert.assertEquals(0, hashTable.size());
        for (int i = 0; i < 10; ++i)
            hashTable.insert(0, 0);
        Assert.assertEquals(1, hashTable.size());
    }

    @Test
    public void testInsertMany() {
        Assert.assertEquals(0, hashTable.size());
        for (int i = 0; i < 1000; ++i)
            hashTable.insert(i, i);
        for (int i = 0; i < 1000; i += 2)
            hashTable.remove(i);
        Assert.assertEquals(500, hashTable.size());
        for (int i = 0; i < 1000; i += 2)
            Assert.assertNull(hashTable.find(i));
        for (int i = 1; i < 1000; i += 2)
            Assert.assertEquals(i, (int) hashTable.find(i));

    }

}
