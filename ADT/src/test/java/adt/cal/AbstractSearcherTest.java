package adt.cal;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractSearcherTest {    
    protected Searcher searcher;
    
    protected AbstractSearcherTest(Searcher searcher) {
        this.searcher = searcher;
    }
    
    @Test
    public void testEmpty() {
        int[] vs = new int[0];
        Assert.assertFalse(searcher.contains(0, vs));
        Assert.assertFalse(searcher.contains(1, vs));
        Assert.assertFalse(searcher.contains(2, vs));
        Assert.assertFalse(searcher.contains(-1, vs));
        Assert.assertFalse(searcher.contains(-2, vs));
        Assert.assertFalse(searcher.contains(Integer.MAX_VALUE, vs));
        Assert.assertFalse(searcher.contains(Integer.MIN_VALUE, vs));
    }
    
    @Test
    public void testSingleton() {
        int[] vs1 = new int[] { 0 };
        int[] vs2 = new int[] { 1 };
        Assert.assertTrue(searcher.contains(0, vs1));
        Assert.assertFalse(searcher.contains(1, vs1));
        Assert.assertFalse(searcher.contains(2, vs1));
        Assert.assertFalse(searcher.contains(-1, vs1));
        Assert.assertFalse(searcher.contains(-2, vs1));
        Assert.assertFalse(searcher.contains(Integer.MAX_VALUE, vs1));
        Assert.assertFalse(searcher.contains(Integer.MIN_VALUE, vs1));

        Assert.assertFalse(searcher.contains(0, vs2));
        Assert.assertTrue(searcher.contains(1, vs2));
        Assert.assertFalse(searcher.contains(2, vs2));
        Assert.assertFalse(searcher.contains(-1, vs2));
        Assert.assertFalse(searcher.contains(-2, vs2));
        Assert.assertFalse(searcher.contains(Integer.MAX_VALUE, vs2));
        Assert.assertFalse(searcher.contains(Integer.MIN_VALUE, vs2));
    }
    
    @Test
    public void testRandom() {
        Random random = new Random();
        
        for (int n = 5; n < 15; ++n) {
            int[] vs = new int[n];
            for (int i = 0; i < n; ++i)
                vs[i] = random.nextInt();
            Arrays.sort(vs);
            
            for (int i = 1; i + 1 < n; ++i) {
                Assert.assertTrue(searcher.contains(vs[i], vs));
                Assert.assertEquals(vs[i-1] == vs[i] - 1, searcher.contains(vs[i] - 1, vs));
                Assert.assertEquals(vs[i+1] == vs[i] + 1, searcher.contains(vs[i] + 1, vs));
            }
        }
    }
}
