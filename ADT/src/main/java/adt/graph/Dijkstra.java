package adt.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra implements ShortestPathFinder {

    @Override
    public Map<Vertex, Double> find(Graph g, Vertex s) {
        Map<Vertex, Double> p = new HashMap<Vertex, Double>();
        PriorityQueue<Edge> q = new PriorityQueue<Edge>();

        // 最短距離が分かっている集合 S と、わかってない集合 V - S がある
        // 証明をかく必要がある。
        // あと、きれいな絵を書く
        
        q.add(new Edge(s, s, 0));
        while (!q.isEmpty()) {
            Edge e = q.poll();

            // 既にあったら continue
            if (p.containsKey(e.end)) { continue; }
            p.put(e.end, e.weight);
            
            
            double cost = e.weight;
            for (Edge edge : g.edges(e.end)) {
                if (p.containsKey(edge.end)) { continue; }
                q.add(new Edge(edge.start, edge.end, cost + edge.weight));
            }
        }

        return p;
    }
    
    @Override
    public boolean acceptsNegativeEdge() {
        return false;
    }
}
