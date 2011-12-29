package adt.graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private int size;
    private Vertex[] vs;
    private List<Edge>[] es;
    
    @SuppressWarnings("unchecked")
    public Graph(int size) {
        this.size = size;
        this.vs = new Vertex[size];
        for (int i = 0; i < size; ++i) {
            this.vs[i] = new Vertex(i);
        }
        
        this.es = new List[size];
        for (int i = 0; i < size; ++i) {
            this.es[i] = new ArrayList<Edge>();
        }
    }

    public int size() {
        return this.size;
    }

    public Vertex vertex(int id) {
        return vs[id];
    }
    
    public Vertex[] vertices() {
        return vs;
    }
    
    public List<Edge> edges(Vertex v) {
        return es[v.id];
    }
    
    public void addEdge(Edge e) {
        es[e.s.id].add(e);
    }
    
}
