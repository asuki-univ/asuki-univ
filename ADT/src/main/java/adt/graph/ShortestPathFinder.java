package adt.graph;


public interface ShortestPathFinder {
    public Result find(Graph g, Vertex s);
    public boolean acceptsNegativeEdge();
}
