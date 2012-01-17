package adt.cal.impl;

import adt.cal.PowModFinder;

public class BinaryPowModFinder implements PowModFinder {
    public int powmod(int a, int k, int m) {
        if (k == 0) { return 1; }
        
        long t = powmod(a, k / 2, m);
        t = t * t % m;
        if (k % 2 == 1)
            t = t * a % m;

        return (int) t;            
    }    

}
