package adt.graph;

import java.util.HashMap;
import java.util.Map;

public class Floyd implements ShortestPathFinder {

    @Override
    public Result find(Graph g, Vertex s) {
        int size = g.size();
        Vertex[] vs = g.vertices();
        
        double[][] cs = new double[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                cs[i][j] = Double.POSITIVE_INFINITY;
            }
            cs[i][i] = 0.0;
        }

        for (Vertex v : vs) {
            for (Edge e : g.getEdges(v)) {
                cs[e.s.id][e.e.id] = e.weight();
            }
        }
        
        // TODO: なぜこれで最短距離を求めることが出来るのかの説明が必要。
        
        for (int k = 0; k < size; ++k) {
            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) {
                    cs[i][j] = Math.min(cs[i][j], cs[i][k] + cs[k][j]);
                }
            }
        }
        
        Map<Vertex, Integer> p = new HashMap<Vertex, Integer>();
        for (int i = 0; i < size; ++i) {
            p.put(vs[i], (int) cs[s.id][i]);
        }
        
        return new Result(p, false);
    }
    
    @Override
    public boolean acceptsNegativeEdge() {
        return false;
    }
}
