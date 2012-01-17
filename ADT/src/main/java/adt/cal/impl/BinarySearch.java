package adt.cal.impl;

import adt.cal.Searcher;

public class BinarySearch implements Searcher {

    public boolean contains(int v, int[] vs) {
        if (vs.length == 0)
            return false;
        
        int left = 0, right = vs.length;
        while (left + 1 < right) {
            int mid = (left + right) / 2;

            if (v < vs[mid])
                right = mid;
            else
                left = mid;
        }
        
        return v == vs[left];
    }
}
