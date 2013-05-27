package adt.sort;

public class QuickSort implements Sorter {

    public void sort(int[] x) {
        if (x == null) { return; }
        sortInner(x, 0, x.length);
    }
    
    private void sortInner(int[] x, int left, int right) {
        // 配列の長さが１以下であればソートの必要はない
        int len = right - left;
        if (len <= 1) { return; }
        
        // ピボットを選択。選択できなければ -1 を返す。
        int pivotIdx = findPivotIdx(x, left, right);
        if (pivotIdx < 0) { return; }

        // ピボットで分割
        int mid = partition(x, left, right, x[pivotIdx]);
        
        // 分割した配列を再びソート
        if (mid - left >= 2) {
            sortInner(x, left, mid);
        }
        if (right - mid >= 2) {
            sortInner(x, mid, right);
        }
    }
    
    // [left, right) の範囲で異なる２つの値を見つけ、小さい方を pivot とする。全て同じ値であれば -1 を返す。
    public int findPivotIdx(int[] x, int left, int right) {
        if (right - left <= 1) { return -1; }
        int t = x[left];
        for (int i = left + 1; i < right; ++i) {
            if (x[i] == t) { continue; }
            
            if (x[i] < t) { return i; }
            else { return left; }            
        }
        
        return -1;
    }
    
    // [left, right) の範囲で [left, mid) の範囲で pivot *以下*, [mid, right) の範囲で pivot より上となるように x を入れかえ、mid を返す。 
    public int partition(int[] x, int left, int right, int pivot) {
        int il = left;
        int ir = right - 1;
        
        while (il < ir) {
            while (il < right && x[il] <= pivot) { ++il; }
            while (left <= ir && pivot < x[ir])  { --ir; }
            
            if (il >= ir) { break; }
            
            // now x[ir] <= pivot < x[il]
            int t = x[il];
            x[il] = x[ir];
            x[ir] = t;
        }
        
        return ir + 1;
    }
}
