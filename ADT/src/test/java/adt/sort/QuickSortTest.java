package adt.sort;

import org.junit.Assert;
import org.junit.Test;

import adt.sort.QuickSort;



public class QuickSortTest extends SortTest {
    public QuickSortTest() {
        super(new QuickSort());
    }
    
    @Test
    public void testToFindPivotIndex() throws Exception {
        {
            int[] vs = new int[] { 0, 0, 0, 0, 0 };
            Assert.assertEquals(-1, new QuickSort().findPivotIdx(vs, 0, vs.length));
        }        
        {
            int[] vs = new int[] { 0, 1, 0, 0, 0 };
            Assert.assertEquals(0, new QuickSort().findPivotIdx(vs, 0, vs.length));
        }        
        {
            int[] vs = new int[] { 0, 0, 0, 0, -1 };
            Assert.assertEquals(4, new QuickSort().findPivotIdx(vs, 0, vs.length));
        }                
    }
    
    @Test
    public void testToPartition() throws Exception {
        {
            int[] vs = { 0, 1, 2, 3, 4 };
            Assert.assertEquals(1, new QuickSort().partition(vs, 0, vs.length, 0));
        }
        {
            int[] vs = { 0, 1, 2, 3, 4 };
            Assert.assertEquals(2, new QuickSort().partition(vs, 0, vs.length, 1));
        }
        {
            int[] vs = { 0, 1, 2, 3, 4 };
            Assert.assertEquals(5, new QuickSort().partition(vs, 0, vs.length, 4));
        }        
        {
            int[] vs = { 4, 3, 2, 1, 0 };
            Assert.assertEquals(1, new QuickSort().partition(vs, 0, vs.length, 0));
        }
        {
            int[] vs = { 4, 3, 0, 1, 2 };
            int pivot = 0;
            Assert.assertEquals(1, new QuickSort().partition(vs, 0, vs.length, pivot));
            for (int i = 0; i < 1; ++i) {
                Assert.assertTrue(vs[i] <= pivot); 
            }
            for (int i = 1; i < vs.length; ++i) {
                Assert.assertTrue(pivot < vs[i]);
            }
        }
    }
}
