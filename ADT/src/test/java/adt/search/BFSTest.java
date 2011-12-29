package adt.search;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import adt.graph.Edge;
import adt.graph.Graph;
import adt.graph.Vertex;
import adt.search.BreadthFirstSearch;

public abstract class BFSTest {
    private BreadthFirstSearch bfs;
    
    protected BFSTest(BreadthFirstSearch bfs) {
        this.bfs = bfs;
    }

    @Test
    public void testFindSingle() {
        Graph g = new Graph(1);
        
        Vertex[] iters = toArray(bfs.iter(g, g.vertex(0)));

        for (int i = 0; i < g.size(); ++i) {
            Assert.assertEquals(g.vertex(i), iters[i]);
        }
    }

    @Test
    public void testFindDouble() {
        Graph g = new Graph(2);
        g.addEdge(new Edge(g.vertex(0), g.vertex(1)));
        
        Vertex[] iters = toArray(bfs.iter(g, g.vertex(0)));

        for (int i = 0; i < g.size(); ++i) {
            Assert.assertEquals(g.vertex(i), iters[i]);
        }
    }

    @Test   
    public void testFindTriple() {
        Graph g = new Graph(3);
        g.addEdge(new Edge(g.vertex(0), g.vertex(1)));
        g.addEdge(new Edge(g.vertex(0), g.vertex(2)));
                
        Vertex[] iters = toArray(bfs.iter(g, g.vertex(0)));

        for (int i = 0; i < g.size(); ++i) {
            Assert.assertEquals(g.vertex(i), iters[i]);
        }
    }

    @Test   
    public void testFind7() {
        Graph g = new Graph(7);
        g.addEdge(new Edge(g.vertex(0), g.vertex(1))); {
            g.addEdge(new Edge(g.vertex(1), g.vertex(3)));
            g.addEdge(new Edge(g.vertex(1), g.vertex(4)));
        }
        g.addEdge(new Edge(g.vertex(0), g.vertex(2))); {
            g.addEdge(new Edge(g.vertex(2), g.vertex(5)));
            g.addEdge(new Edge(g.vertex(2), g.vertex(6)));
        }
                
        Vertex[] iters = toArray(bfs.iter(g, g.vertex(0)));

        for (int i = 0; i < g.size(); ++i) {
            Assert.assertEquals(g.vertex(i), iters[i]);
        }
    }

    @Test
    public void testFindLoop1() {
        Graph g = new Graph(3);
        g.addEdge(new Edge(g.vertex(0), g.vertex(1)));
        g.addEdge(new Edge(g.vertex(1), g.vertex(0)));
        g.addEdge(new Edge(g.vertex(0), g.vertex(2)));
        
        Vertex[] iters = toArray(bfs.iter(g, g.vertex(0)));
        
        for (int i = 0; i < g.size(); ++i) {
            Assert.assertEquals(g.vertex(i), iters[i]);
        }
    }
    
    // ----------------------------------------------------------------------
    
    private Vertex[] toArray(Iterable<Vertex> ns) {
        List<Vertex> vs = new ArrayList<Vertex>();
        
        for (Iterator<Vertex> it = ns.iterator(); it.hasNext(); ) { 
            vs.add(it.next());
        }
        return vs.toArray(new Vertex[0]);
    }
}
