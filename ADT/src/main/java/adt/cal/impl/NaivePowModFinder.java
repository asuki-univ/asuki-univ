package adt.cal.impl;

import adt.cal.PowModFinder;

public class NaivePowModFinder implements PowModFinder {

    public int powmod(int a, int k, int m) {
        long t = 1;
        for (int i = 0; i < k; ++i) {
            t = (t * a) % m;
        }
        
        return (int) t;
    }

}
