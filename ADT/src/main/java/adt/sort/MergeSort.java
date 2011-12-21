package adt.sort;


public class MergeSort implements Sorter {

    public void sort(int[] x) {
       if (x == null) { return; }
       sortInner(x, 0, x.length);
    }
    
    private void sortInner(int[] x, int left, int right) {
        if (right - left <= 1) { return; }
        
        int mid = (left + right) / 2;
        sortInner(x, left, mid);
        sortInner(x, mid, right);
        merge(x, left, mid, right);
    }
    
    private void merge(int[] x, int left, int mid, int right) {
        int n = right - left;
        int[] work = new int[n];
        
        int iw = 0;
        int il = left;
        int ir = mid;
        
        while (il < mid && ir < right) {
            if (x[il] < x[ir]) {
                work[iw++] = x[il++];
            } else {
                work[iw++] = x[ir++];
            }
        }
        
        while (il < mid) {
            work[iw++] = x[il++];
        }
        while (ir < right) {
            work[iw++] = x[ir++];
        }
        
        System.arraycopy(work, 0, x, left, n);
    }
}
