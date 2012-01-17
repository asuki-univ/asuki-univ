package adt.graph;

import java.util.HashMap;
import java.util.Map;

public class Floyd implements ShortestPathFinder {

    @Override
    public Map<Vertex, Double> find(Graph g, Vertex s) {
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
            for (Edge e : g.edges(v)) {
                cs[e.start.id][e.end.id] = e.weight;
            }
        }
        
        for (int k = 0; k < size; ++k) {
            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) {
                    cs[i][j] = Math.min(cs[i][j], cs[i][k] + cs[k][j]);
                }
            }
        }
        
        Map<Vertex, Double> p = new HashMap<Vertex, Double>();
        for (int i = 0; i < size; ++i) {
            p.put(vs[i], cs[s.id][i]);
        }
        
        return p;
    }
    
    @Override
    public boolean acceptsNegativeEdge() {
        return false;
    }
}
