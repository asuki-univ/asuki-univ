package adt.graph;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractMSTFinderTest {
    private MSTFinder finder;
    
    protected AbstractMSTFinderTest(MSTFinder finder) {
        this.finder = finder;
    }
    
    @Test
    public void testMST1() {
        final int N = 5;
        Graph g = new Graph(N);
        for (int i = 1; i < N; ++i) {
            g.addEdge(new Edge(g.vertex(0), g.vertex(i), 1));
            g.addEdge(new Edge(g.vertex(i), g.vertex(0), 1));
        }
        
        List<Edge> mst = finder.find(g);
        Assert.assertEquals(N-1, mst.size());
        for (int i = 1; i < N; ++i) {
            boolean ok = false;
            for (Edge e : mst) {
                if (e.s == g.vertex(0) && e.e == g.vertex(i))
                    ok = true;
                else if (e.s == g.vertex(i) && e.e == g.vertex(0))
                    ok = true;
            }
            Assert.assertTrue(ok);
        }
    }
    
    @Test
    public void testMST2() {
        Graph g = new Graph(6);
        
        int[][] costs = new int[][] {
            { 0, 1, 1, 4, 0, 0 },
            { 1, 0, 1, 0, 2, 0 },
            { 1, 1, 0, 1, 7, 4 },
            { 4, 0, 1, 0, 0, 0 },
            { 0, 2, 7, 0, 0, 3 },
            { 0, 0, 4, 0, 3, 0 },
        };
        
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 6; ++j) {
                if (costs[i][j] == 0)
                    continue;
                g.addEdge(new Edge(g.vertex(i), g.vertex(j), costs[i][j]));
            }
        }
        
        int cost = 0; 
        List<Edge> es = finder.find(g);
        for (Edge e : es) {
            cost += e.weight();
        }
        Assert.assertEquals(5, es.size());
        Assert.assertEquals(8, cost);
    }
}
