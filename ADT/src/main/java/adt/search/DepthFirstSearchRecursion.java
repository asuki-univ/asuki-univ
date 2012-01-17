package adt.search;


import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import adt.graph.Edge;
import adt.graph.Graph;
import adt.graph.Vertex;

public class DepthFirstSearchRecursion implements DepthFirstSearch {
    @Override
    public Iterable<Vertex> iter(Graph g, Vertex start) {
        if (start == null) { return null; }
        
        Set<Vertex> visited = new LinkedHashSet<Vertex>();
        iter(g, start, visited);
        return visited;
    }
    
    public void iter(Graph g, Vertex v, Set<Vertex> visited) {
        if (visited.contains(v)) { return; }
        visited.add(v);
        
        List<Edge> edges = g.edges(v);
        for (int i = 0; i < edges.size(); ++i) {
            Edge edge = edges.get(i);
            iter(g, edge.end, visited);
        }
    }    
}
