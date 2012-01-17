package adt.graph;

import java.util.HashMap;
import java.util.Map;

public class BellmanFord implements ShortestPathFinder {

    @Override
    public Map<Vertex, Double> find(Graph g, Vertex s) {
        Map<Vertex, Double> p = new HashMap<Vertex, Double>();

        // 開始点以外は安全に距離を無限大にしておく
        for (Vertex v : g.vertices())
            p.put(v, Double.POSITIVE_INFINITY);        
        // 開始点の距離は 0 とする。
        p.put(s, 0.0);

        int cnt;
        for (cnt = 0; cnt < g.size(); ++cnt) {
            if (!update(g, p))
                break;
        }

        if (cnt == g.size())
            throw new NegativeLoopDetected();
        
        return p;
    }
    
    private boolean update(Graph g, Map<Vertex, Double> p) {
        boolean changed = false;
        for (Vertex v : g.vertices()) {
            for (Edge e : g.edges(v)) {
                double oldCost = p.get(e.end);
                double newCost = p.get(e.start) + e.weight;
                if (newCost < oldCost) {
                    p.put(e.end, newCost);
                    changed = true;
                }
            }
        }

        return changed;
    }
    
    @Override
    public boolean acceptsNegativeEdge() {
        return true;
    }
}
