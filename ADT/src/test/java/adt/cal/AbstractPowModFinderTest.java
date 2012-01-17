package adt.cal;

import java.math.BigInteger;

import org.junit.Test;
import org.junit.Assert;

public abstract class AbstractPowModFinderTest {

    private PowModFinder finder;
    
    protected AbstractPowModFinderTest(PowModFinder finder) {
        this.finder = finder;
    }
    
    @Test
    public void testSmallExhaustive() {
        for (int a = 0; a < 10; ++a) {
            for (int k = 0; k < 10; ++k) {
                for (int m = 2; m < 10; ++m) {
                    Assert.assertEquals(powmodBigInteger(a, k, m), finder.powmod(a, k, m));
                }
            }
        }       
    }
    
    @Test
    public void testLarge() {
        Assert.assertEquals(powmodBigInteger(Integer.MAX_VALUE, 100, Integer.MAX_VALUE), finder.powmod(Integer.MAX_VALUE, 100, Integer.MAX_VALUE));
        Assert.assertEquals(powmodBigInteger(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE), finder.powmod(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE));
    }
    
    public static int powmodBigInteger(int a, int k, int m) {
        if (k == 0) { return 1; }
        
        BigInteger t = BigInteger.valueOf(powmodBigInteger(a, k / 2, m));
        t = t.multiply(t).mod(BigInteger.valueOf(m));
        if (k % 2 == 1)
            t = t.multiply(BigInteger.valueOf(a)).mod(BigInteger.valueOf(m));
        
        return t.intValue();
    }

}
