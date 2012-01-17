package adt.graph;

import java.util.Map;

class NegativeLoopDetected extends RuntimeException {
    private static final long serialVersionUID = 1L;
}

public interface ShortestPathFinder {
    public Map<Vertex, Double> find(Graph g, Vertex s);
    public boolean acceptsNegativeEdge();
}
