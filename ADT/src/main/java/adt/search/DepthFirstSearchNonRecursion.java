package adt.search;


import java.util.LinkedHashSet;
import java.util.List;
import java.util.Stack;

import adt.graph.Edge;
import adt.graph.Graph;
import adt.graph.Vertex;

public class DepthFirstSearchNonRecursion implements DepthFirstSearch {

    @Override
    public Iterable<Vertex> iter(Graph g, Vertex start) {
        if (start == null) { return null; }
        
        Stack<Vertex> q = new Stack<Vertex>();
        q.add(start);
        
        LinkedHashSet<Vertex> visited = new LinkedHashSet<Vertex>();
        
        while (!q.isEmpty()) {
            Vertex v = q.pop();
            if (visited.contains(v)) { continue; }
            visited.add(v);
            
            List<Edge> edges = g.getEdges(v);
            for (int i = edges.size() - 1; i >= 0; --i) {
                Edge edge = edges.get(i);
                q.add(edge.e);
            }
        }
        return visited;
    }    
}
