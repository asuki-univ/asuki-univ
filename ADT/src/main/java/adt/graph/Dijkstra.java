package adt.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra implements ShortestPathFinder {

    @Override
    public Result find(Graph g, Vertex s) {
        Map<Vertex, Integer> p = new HashMap<Vertex, Integer>();
        PriorityQueue<Edge> q = new PriorityQueue<Edge>();

        // 最短距離が分かっている集合 S と、わかってない集合 V - S がある
        // 証明をかく必要がある。
        // あと、きれいな絵を書く
        
        q.add(new Edge(s, s, 0));
        while (!q.isEmpty()) {
            Edge e = q.poll();

            // 既にあったら continue
            if (p.containsKey(e.e)) { continue; }
            p.put(e.e, e.weight());
            
            
            int cost = e.weight();
            for (Edge edge : g.edges(e.e)) {
                if (p.containsKey(edge.e)) { continue; }
                q.add(new Edge(edge.s, edge.e, cost + edge.weight()));
            }
        }
                
        return new Result(p, false);
    }
    
    @Override
    public boolean acceptsNegativeEdge() {
        return false;
    }
}
