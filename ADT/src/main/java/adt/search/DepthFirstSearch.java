package adt.search;

import adt.graph.Graph;
import adt.graph.Vertex;

public interface DepthFirstSearch {
    public Iterable<Vertex> iter(Graph g, Vertex start);
}
