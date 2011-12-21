package adt.datastruct;

import java.util.Arrays;
import java.util.Random;

import junit.framework.Assert;

import org.junit.Test;

public class HeapTest {

    @Test
    public void testHeapSequence() {
        Heap heap = new Heap(10);
        for (int i = 0; i < 10; ++i)
            heap.push(i);
        
        for (int i = 0; i < 10; ++i) {
            int[] vs = heap.debugValuess();
            for (int j = 0; j < vs.length; ++j) {
                System.out.printf("%d ", vs[j]);
            }
            System.out.println();

            Assert.assertEquals(i, heap.pop());
            
        }
    }
    
    @Test
    public void testHeapRevSequence() {
        Heap heap = new Heap(10);
        for (int i = 0; i < 10; ++i)
            heap.push(9-i);
        
        for (int i = 0; i < 10; ++i) {
            Assert.assertEquals(i, heap.pop());
        }
    }
    
    @Test
    public void testHeapRandom() {
        Heap heap = new Heap(100);
        Random random = new Random(0);
        
        int[] vs = new int[100];
        for (int i = 0; i < 100; ++i)
            heap.push(vs[i] = random.nextInt());
        
        Arrays.sort(vs);
        for (int i = 0; i < 100; ++i) {
            Assert.assertEquals(vs[i], heap.pop());
        }
    }
    
}
