package adt.sort;

import adt.datastruct.Heap;

public class HeapSort implements Sorter {

    @Override
    public void sort(int[] values) {
        Heap heap = new Heap(values.length);
        for (int i = 0; i < values.length; ++i)
            heap.push(values[i]);

        for (int i = 0; i < values.length; ++i)
            values[i] = heap.pop();        
    }
}
