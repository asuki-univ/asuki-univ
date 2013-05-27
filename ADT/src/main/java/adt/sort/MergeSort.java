package adt.sort;


public class MergeSort implements Sorter {

    public void sort(int[] x) {
       if (x == null) { return; }
       sortInner(x, 0, x.length);
    }
    
    // x のうち、left から right の間をソート
    private void sortInner(int[] x, int left, int right) {
        // 配列の大きさが 1 以下であればソート済み。
        if (right - left <= 1) { return; }
        
        // 配列を left ~ mid, mid ~ right の２つに分けてマージソート
        int mid = left + (right - left) / 2;
        sortInner(x, left, mid);
        sortInner(x, mid, right);

        // 配列をマージ
        merge(x, left, mid, right);
    }
    
    private void merge(int[] x, int left, int mid, int right) {
        int n = right - left;
        int[] work = new int[n];
        
        int iw = 0;
        int il = left;
        int ir = mid;
        
        // ２つの配列から、小さい方を選ぶ。同じであれば１つめの配列から選ぶ。
        while (il < mid && ir < right) {
            if (x[il] < x[ir]) {
                work[iw++] = x[il++];
            } else {
                work[iw++] = x[ir++];
            }
        }
        
        // 選ばれてない配列の要素を一時領域に入れる
        while (il < mid) {
            work[iw++] = x[il++];
        }
        while (ir < right) {
            work[iw++] = x[ir++];
        }
        
        // 一時領域からコピーし直す。
        System.arraycopy(work, 0, x, left, n);
    }
}
