package adt.search;


import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import adt.graph.Edge;
import adt.graph.Graph;
import adt.graph.Vertex;

public class BreadthFirstSearch {

    public Iterable<Vertex> iter(Graph g, Vertex start) {
        if (start == null) { return null; }
        
        LinkedHashSet<Vertex> visited = new LinkedHashSet<Vertex>();
        Queue<Vertex> q = new LinkedList<Vertex>();
        q.add(start);
        
        while (!q.isEmpty()) {
            Vertex v = q.poll();
            if (visited.contains(v)) { continue; }
            visited.add(v);
            
            List<Edge> edges = g.edges(v);
            for (Edge e : edges) {
                if (visited.contains(e.e)) { continue; }
                q.add(e.e);
            }
        }
        
        return visited;
    }
    
}
