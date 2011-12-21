package adt.sort;

public class InsertionSort implements Sorter {

    public void sort(int[] x) {
       if (x == null) { return; }
       int N = x.length;

       for (int i = 1; i < N; ++i) {
           // [x[0], x[i]) はソート済
           // NOTE: ここは代入回数を減らすことが可能
           
           int j = i;
           while (j > 0 && x[j-1] > x[j]) {
               int t = x[j];
               x[j] = x[j-1];
               x[j-1] = t;
               --j;
           }
       }
       
    }
}
