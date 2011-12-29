package adt.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import adt.datastruct.UnionFind;

public class Kruskal implements MSTFinder {

    @Override
    public List<Edge> find(Graph g) {
        Vertex[] vs = g.vertices();
        
        UnionFind<Vertex> uf = new UnionFind<Vertex>();        
        
        ArrayList<Edge> edges = new ArrayList<Edge>();
        for (Vertex v : vs) {
            uf.addNode(v);
            for (Edge e : g.edges(v)) {
                edges.add(e);
            }
        }
        Collections.sort(edges);
        
        ArrayList<Edge> result = new ArrayList<Edge>();
        for (Edge edge : edges) {
            if (uf.isSame(edge.s, edge.e))
                continue;
            
            uf.unify(edge.s, edge.e);
            result.add(edge);
        }
        
        return result;
    }
}
