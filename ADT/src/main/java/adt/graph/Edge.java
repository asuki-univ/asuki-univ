package adt.graph;

public class Edge implements Comparable<Edge> {
    public Vertex start;
    public Vertex end;
    public double weight;
    
    public Edge(Vertex s, Vertex e) {
        this(s, e, 1);
    }
    
    public Edge(Vertex s, Vertex e, double w) {
        this.start = s;
        this.end = e;
        this.weight = w;
    }
    
    @Override
    public int compareTo(Edge o) {
        Edge lhs = this;
        Edge rhs = o;
        
        if (lhs.weight != rhs.weight)
            return lhs.weight < rhs.weight ? -1 : 1;

        return 0;
    }
    
    @Override
    public String toString() {
        return String.format("e(%s->%s;%f)", start.toString(), end.toString(), weight); 
    }
}

