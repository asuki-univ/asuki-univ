package adt.datastruct;

import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractBalancedTreeTest {
    private AbstractBalancedTree tree;
    
    protected AbstractBalancedTreeTest(AbstractBalancedTree tree) {
        this.tree = tree;
    }
    
    @Test
    public void testEmpty() {
        Assert.assertTrue(tree.check());
    }
    
    @Test
    public void testInsert() {
        for (int i = 0; i < 10; ++i)
            tree.insert(i);

        Assert.assertTrue(tree.check());
        
        for (int i = 0; i < 10; ++i)
            Assert.assertTrue(tree.contains(i));
        
        for (int i = 0; i < 10; ++i)
            tree.remove(i);
        
        Assert.assertTrue(tree.check());
    }
}
