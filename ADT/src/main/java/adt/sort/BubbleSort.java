package adt.sort;

public class BubbleSort implements Sorter {

    public void sort(int[] x) {
       if (x == null) { return; }
       int N = x.length;
       
       // ループの条件に注意！
       for (int i = N; i > 0; --i) {
           for (int j = 1; j < i; ++j) {
               if (x[j-1] > x[j]) {
                   int t = x[j-1];
                   x[j-1] = x[j];
                   x[j] = t;
               }
           }
       }
    }
}
