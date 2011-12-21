package adt.sort;

public class SelectionSort implements Sorter {

    public void sort(int[] x) {
       if (x == null) { return; }
       int N = x.length;

       for (int i = 0; i < N; ++i) {
           int minIdx = i;
           for (int j = i + 1; j < N; ++j) {
               if (x[j] < x[minIdx]) {
                   minIdx = j;
               }
           }
           
           int t = x[i];
           x[i] = x[minIdx];
           x[minIdx] = t;
       }
    }
}
