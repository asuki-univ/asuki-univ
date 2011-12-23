package adt.sort;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import adt.sort.Sorter;


public abstract class SortTest {
    protected Sorter sorter;
    
    protected SortTest(Sorter sorter) {
        this.sorter = sorter;
    }

    @Test
    public void testToSortNullList() throws Exception {
        int[] actuals = new int[0];
        int[] expecteds = new int[0];

        sorter.sort(actuals);
        Assert.assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void testToSortSingleton() throws Exception {
        int[] values = new int[] { 0, 1, -1, Integer.MAX_VALUE, Integer.MIN_VALUE };
        
        for (int v : values) {
            int[] actuals = new int[] { v };
            int[] expecteds = new int[] { v };

            sorter.sort(actuals);
            
            Assert.assertArrayEquals(expecteds, actuals);
        }
    }

    @Test
    public void testToSortDoublet() throws Exception {
        int[][] valuess = new int[][] {
                new int[] { 0, 0 },
                new int[] { -1, 1 },
                new int[] { 1, -1 }
        };
        
        for (int[] values : valuess) {
            int[] actuals = Arrays.copyOf(values, values.length);
            int[] expecteds = Arrays.copyOf(values, values.length);
            
            sorter.sort(actuals);
            Arrays.sort(expecteds);
            
            Assert.assertArrayEquals(expecteds, actuals);
        }
    }

    @Test
    public void testToSortTriplet() throws Exception {
        int[][] valuess = new int[][] {
                new int[] {  0,  0,  0 },
                new int[] { -1,  0,  1 },
                new int[] {  1,  0, -1 },
                new int[] {  0, -1,  1 },
                new int[] {  1, -1,  0 },
        };
        
        for (int[] values : valuess) {
            int[] actuals = Arrays.copyOf(values, values.length);
            int[] expecteds = Arrays.copyOf(values, values.length);
            
            sorter.sort(actuals);
            Arrays.sort(expecteds);
            
            Assert.assertArrayEquals(expecteds, actuals);
        }
    }
    
    @Test
    public void testToSortRandom() throws Exception {
        testToSortRandom(5);
        testToSortRandom(7);
        testToSortRandom(8);
        testToSortRandom(9);
        testToSortRandom(15);
        testToSortRandom(16);
        testToSortRandom(17);
        testToSortRandom(100);
        testToSortRandom(128);
        testToSortRandom(256);
        testToSortRandom(257);
        testToSortRandom(1000);
    }
  
    private void testToSortRandom(int N) throws Exception {
        Random random = new Random();
        
        int[] actuals = new int[N];
        int[] expecteds = new int[N];
        for (int i = 0; i < N; ++i)
            expecteds[i] = actuals[i] = random.nextInt();

        Arrays.sort(expecteds);
        sorter.sort(actuals);
        
        Assert.assertArrayEquals(expecteds, actuals);
    }
}
