package adt.datastruct;

import org.junit.Assert;
import org.junit.Test;

public class UnionFindTest {

    @Test
    public void testSingle() {
        UnionFind<Integer> uf = new UnionFind<Integer>();
        for (int i = 0; i < 10; ++i)
            uf.addNode(i);
        
        for (int i = 0; i < 10; ++i)
            for (int j = 0; j < 10; ++j)
                Assert.assertEquals(i == j, uf.isSame(i, j));
    }
    
    @Test
    public void testDouble() {
        UnionFind<Integer> uf = new UnionFind<Integer>();
        for (int i = 0; i < 10; ++i)
            uf.addNode(i);
        
        for (int i = 1; i < 5; ++i)
            uf.unify(0, i);
        for (int i = 6; i < 10; ++i)
            uf.unify(5, i);

        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                boolean s1 = i < 5, s2 = j < 5;
                Assert.assertEquals(s1 == s2, uf.isSame(i, j));
            }
        }
    }
    
}
