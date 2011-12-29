package adt.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Prim implements MSTFinder {
    @Override
    public List<Edge> find(Graph g) {
        PriorityQueue<Edge> q = new PriorityQueue<Edge>();
        Set<Vertex> visited = new HashSet<Vertex>();
        List<Edge> result = new ArrayList<Edge>();

        // ダミーエッジを Priority Queue に入れ、これを初期頂点として用いる。
        Edge dummy = new Edge(new Vertex(-1), g.vertex(0));
        q.add(dummy);
        
        while (!q.isEmpty() && result.size() < g.size()) {
            Edge edge = q.poll();
            if (visited.contains(edge.e))
                continue;
            
            visited.add(edge.e);
            result.add(edge);
            for (Edge e : g.edges(edge.e)) {
                if (visited.contains(e.e))
                    continue;
                q.add(e);
            }            
        }
        
        result.remove(dummy);
        
        if (result.size() != g.size() - 1)
            throw new RuntimeException("No ");
        
        return result;
    }
}
