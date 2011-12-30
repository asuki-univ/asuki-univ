package adt.graph;

import org.junit.Assert;
import org.junit.Test;

public class MaxFlowTest {

    @Test
    public void testSimple() {
        Graph g = new Graph(2);
        g.addEdge(new Edge(g.vertex(0), g.vertex(1), 2));
        
        int[][] flow = new MaxFlow().findMaxFlow(g, g.vertex(0), g.vertex(1));
        
        Assert.assertEquals(0, flow[0][0]);
        Assert.assertEquals(2, flow[0][1]);
        Assert.assertEquals(0, flow[1][0]);
        Assert.assertEquals(0, flow[1][1]);        
    }

    @Test
    public void testReverse() {
        Graph g = new Graph(2);
        g.addEdge(new Edge(g.vertex(0), g.vertex(1), 2));
        
        int[][] flow = new MaxFlow().findMaxFlow(g, g.vertex(1), g.vertex(0));
        
        Assert.assertEquals(0, flow[0][0]);
        Assert.assertEquals(0, flow[0][1]);
        Assert.assertEquals(0, flow[1][0]);
        Assert.assertEquals(0, flow[1][1]);                
    }
    
    @Test
    public void testTwoway() {
        Graph g = new Graph(3);
        g.addEdge(new Edge(g.vertex(0), g.vertex(1), 1));
        g.addEdge(new Edge(g.vertex(1), g.vertex(2), 1));
        g.addEdge(new Edge(g.vertex(0), g.vertex(2), 1));
        
        int[][] flow = new MaxFlow().findMaxFlow(g, g.vertex(0), g.vertex(2));
        
        Assert.assertEquals(1, flow[0][1]);
        Assert.assertEquals(1, flow[1][2]);
        Assert.assertEquals(1, flow[0][2]);
    }
    
    @Test
    public void testJunction() {
        Graph g = new Graph(4);
        g.addEdge(new Edge(g.vertex(0), g.vertex(1), 1));
        g.addEdge(new Edge(g.vertex(0), g.vertex(2), 1));
        g.addEdge(new Edge(g.vertex(1), g.vertex(2), Integer.MAX_VALUE));
        g.addEdge(new Edge(g.vertex(1), g.vertex(3), 1));
        g.addEdge(new Edge(g.vertex(2), g.vertex(3), 1));
        
        int[][] flow = new MaxFlow().findMaxFlow(g, g.vertex(0), g.vertex(3));
        
        Assert.assertEquals(1, flow[0][1]);
        Assert.assertEquals(1, flow[0][2]);
        Assert.assertEquals(1, flow[1][3]);
        Assert.assertEquals(1, flow[2][3]);
    }
    
    private void showFlow(int[][] flow) {
        for (int[] xs : flow) {
            for (int x : xs) {
                System.out.print(x);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
