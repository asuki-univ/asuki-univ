package adt.cal.impl;

import adt.cal.Searcher;

public class LinearSearch implements Searcher {

    public boolean contains(int v, int[] vs) {
        for (int i = 0; i < vs.length; ++i) {
            if (vs[i] == v)
                return true;
        }
        
        return false;
    }
}
