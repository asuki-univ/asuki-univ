package adt.graph;

public class Edge implements Comparable<Edge> {
    public Vertex s;
    public Vertex e;
    private int w;
    
    public Edge(Vertex s, Vertex e) {
        this(s, e, 1);
    }
    
    public Edge(Vertex s, Vertex e, int w) {
        this.s = s;
        this.e = e;
        this.w = w;
    }
    
    public Vertex start() {
        return s;
    }
    
    public Vertex end() {
        return e;
    }
    
    public int weight() {
        return w;
    }
    
    @Override
    public int compareTo(Edge o) {
        Edge lhs = this;
        Edge rhs = o;
        
        if (lhs.w != rhs.w) { return lhs.w < rhs.w ? -1 : 1; }

        return 0;
    }
    
    @Override
    public String toString() {
        return String.format("e(%s->%s;%f)", s.toString(), e.toString(), w); 
    }
}

