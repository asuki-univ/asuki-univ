package adt.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MaxFlow {
    public double[][] findMaxFlow(Graph g, Vertex source, Vertex sink) {
        int N = g.size();
        double[][] capacity = new double[N][N];
        for (int i = 0; i < N; ++i) {
            for (Edge e : g.edges(g.vertex(i)))
                capacity[e.start.id][e.end.id] = e.weight;
        }
        double[][] flow = new double[N][N];        
        
        while (true) {
            List<Edge> augmentingPath = findAugmentingPath(g, capacity, flow, source, sink);
            if (augmentingPath == null)
                break;
            
            for (Edge e : augmentingPath)
                flow[e.start.id][e.end.id] += e.weight;            
        }

        return flow;
    }
    
    private List<Edge> findAugmentingPath(Graph g, double[][] capacity, double[][] flow, Vertex source, Vertex sink) {
        final int N = g.size();

        // Vertex(i) へたどり着いたときの正の flow を入れておく。e.s に１つ前の頂点が入り、weight には
        // i へたどり着いたときの最大フローを入れておく。
        Edge[] visited = new Edge[N];
        visited[source.id] = new Edge(new Vertex(-1), new Vertex(source.id), Integer.MAX_VALUE);
        
        Queue<Edge> q = new LinkedList<Edge>();
        for (int i = 0; i < N; ++i) {
            if (i == source.id)
                continue;
            double cap = capacity[source.id][i] - (flow[source.id][i] - flow[i][source.id]);
            if (cap > 0)
                q.add(new Edge(new Vertex(source.id), new Vertex(i), cap));
        }
        
        while (!q.isEmpty()) {
            Edge e = q.poll();
            if (visited[e.end.id] != null)
                continue;
            double cap = capacity[e.start.id][e.end.id] - (flow[e.start.id][e.end.id] - flow[e.end.id][e.start.id]);
            cap = Math.min(cap, visited[e.start.id].weight);
            if (cap <= 0)
                continue;

            visited[e.end.id] = new Edge(e.start, e.end, cap);

            if (e.end.id == sink.id) {
                List<Edge> result = new ArrayList<Edge>();
                Edge edge = visited[sink.id];
                double c = visited[sink.id].weight;
                while (edge.start.id != -1) {
                    result.add(new Edge(edge.start, edge.end, c));
                    edge = visited[edge.start.id];
                }
                Collections.reverse(result);
                return result;
            }
            
            for (int i = 0; i < N; ++i) {
                if (visited[i] != null)
                    continue;
                if (i == e.end.id)
                    continue;
                double c = Math.min(cap, capacity[e.end.id][i] - (flow[e.end.id][i] - flow[i][e.end.id]));
                q.add(new Edge(new Vertex(e.end.id), new Vertex(i), c));
            }
        }
        
        return null;
    }
}
