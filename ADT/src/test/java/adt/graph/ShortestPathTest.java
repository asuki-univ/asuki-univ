package adt.graph;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public abstract class ShortestPathTest {
    protected ShortestPathFinder spf;
    
    protected ShortestPathTest(ShortestPathFinder spf) {
        this.spf = spf;
    }
    
    @Test
    public void testSingle() {
        Graph g = new Graph(1);
        
        Map<Vertex, Double> r = spf.find(g, g.vertex(0));
        
        Assert.assertEquals(Double.valueOf(0), r.get(g.vertex(0)));
    }
    
    @Test
    public void testDouble() {
        Graph g = new Graph(2);
        
        g.addEdge(new Edge(g.vertex(0), g.vertex(1), 1));

        Map<Vertex, Double> r = spf.find(g, g.vertex(0));

        Assert.assertEquals(Double.valueOf(0), r.get(g.vertex(0)));
        Assert.assertEquals(Double.valueOf(1), r.get(g.vertex(1)));        
    }
    
    @Test
    public void testTwoPaths() {
        Graph g = new Graph(4);
        
        g.addEdge(new Edge(g.vertex(0), g.vertex(1), 1));
        g.addEdge(new Edge(g.vertex(1), g.vertex(2), 1));
        g.addEdge(new Edge(g.vertex(2), g.vertex(3), 1));
        g.addEdge(new Edge(g.vertex(0), g.vertex(3), 1));

        Map<Vertex, Double> r = spf.find(g, g.vertex(0));

        Assert.assertEquals(Double.valueOf(0), r.get(g.vertex(0)));
        Assert.assertEquals(Double.valueOf(1), r.get(g.vertex(1)));
        Assert.assertEquals(Double.valueOf(2), r.get(g.vertex(2)));
        Assert.assertEquals(Double.valueOf(1), r.get(g.vertex(3)));
    }

    @Test
    public void testTwoPaths2() {
        Graph g = new Graph(4);
        
        g.addEdge(new Edge(g.vertex(0), g.vertex(1), 1));
        g.addEdge(new Edge(g.vertex(1), g.vertex(2), 1));
        g.addEdge(new Edge(g.vertex(2), g.vertex(3), 1));
        g.addEdge(new Edge(g.vertex(0), g.vertex(3), 5));

        Map<Vertex, Double> r = spf.find(g, g.vertex(0));

        Assert.assertEquals(Double.valueOf(0), r.get(g.vertex(0)));
        Assert.assertEquals(Double.valueOf(1), r.get(g.vertex(1)));
        Assert.assertEquals(Double.valueOf(2), r.get(g.vertex(2)));
        Assert.assertEquals(Double.valueOf(3), r.get(g.vertex(3)));
    }

    @Test
    public void testNegative() {
        if (!spf.acceptsNegativeEdge()) { return; }
        
        Graph g = new Graph(5);
        
        g.addEdge(new Edge(g.vertex(0), g.vertex(1),  1));
        g.addEdge(new Edge(g.vertex(1), g.vertex(2),  1));
        g.addEdge(new Edge(g.vertex(2), g.vertex(3), -1));
        g.addEdge(new Edge(g.vertex(3), g.vertex(4),  1));

        Map<Vertex, Double> r = spf.find(g, g.vertex(0));

        Assert.assertEquals(Double.valueOf(0), r.get(g.vertex(0)));
        Assert.assertEquals(Double.valueOf(1), r.get(g.vertex(1)));
        Assert.assertEquals(Double.valueOf(2), r.get(g.vertex(2)));
        Assert.assertEquals(Double.valueOf(1), r.get(g.vertex(3)));
        Assert.assertEquals(Double.valueOf(2), r.get(g.vertex(4)));
    }

    @Test
    public void testNegativeLoop() {
        if (!spf.acceptsNegativeEdge()) { return; }
        
        Graph g = new Graph(5);
        
        g.addEdge(new Edge(g.vertex(0), g.vertex(0), -1));
        g.addEdge(new Edge(g.vertex(0), g.vertex(1),  1));
        g.addEdge(new Edge(g.vertex(1), g.vertex(2),  1));
        g.addEdge(new Edge(g.vertex(2), g.vertex(3),  1));
        g.addEdge(new Edge(g.vertex(3), g.vertex(4),  1));

        try {
            spf.find(g, g.vertex(0));
            Assert.fail();
        } catch (NegativeLoopDetected e) {
            // OK.
        }
    }

}
