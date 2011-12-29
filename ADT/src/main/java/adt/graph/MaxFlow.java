package adt.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MaxFlow {
    public int[][] findMaxFlow(Graph g, Vertex source, Vertex sink) {
        int N = g.size();
        int[][] capacity = new int[N][N];
        for (int i = 0; i < N; ++i) {
            for (Edge e : g.edges(g.vertex(i)))
                capacity[e.s.id][e.e.id] = e.weight();
        }
        int[][] flow = new int[N][N];        
        
        while (true) {
            List<Edge> augmentingPath = findAugmentingPath(g, capacity, flow, source, sink);
            if (augmentingPath == null)
                break;
            
            for (Edge e : augmentingPath)
                flow[e.s.id][e.e.id] += e.weight();            
        }

        return flow;
    }
    
    private List<Edge> findAugmentingPath(Graph g, int[][] capacity, int[][] flow, Vertex source, Vertex sink) {
        final int N = g.size();

        // Vertex(i) へたどり着いたときの正の flow を入れておく。e.s に１つ前の頂点が入り、weight には
        // i へたどり着いたときの最大フローを入れておく。
        Edge[] visited = new Edge[N];
        visited[source.id] = new Edge(new Vertex(-1), new Vertex(source.id), Integer.MAX_VALUE);
        
        Queue<Edge> q = new LinkedList<Edge>();
        for (int i = 0; i < N; ++i) {
            if (i == source.id)
                continue;
            int cap = capacity[source.id][i] - (flow[source.id][i] - flow[i][source.id]);
            if (cap > 0)
                q.add(new Edge(new Vertex(source.id), new Vertex(i), cap));
        }
        
        while (!q.isEmpty()) {
            Edge e = q.poll();
            if (visited[e.e.id] != null)
                continue;
            int cap = capacity[e.s.id][e.e.id] - (flow[e.s.id][e.e.id] - flow[e.e.id][e.s.id]);
            cap = Math.min(cap, visited[e.s.id].weight());
            if (cap <= 0)
                continue;

            visited[e.e.id] = new Edge(e.s, e.e, cap);

            if (e.e.id == sink.id) {
                List<Edge> result = new ArrayList<Edge>();
                Edge edge = visited[sink.id];
                int c = visited[sink.id].weight();
                while (edge.s.id != -1) {
                    result.add(new Edge(edge.s, edge.e, c));
                    edge = visited[edge.s.id];
                }
                Collections.reverse(result);
                return result;
            }
            
            for (int i = 0; i < N; ++i) {
                if (visited[i] != null)
                    continue;
                if (i == e.e.id)
                    continue;
                int c = Math.min(cap, capacity[e.e.id][i] - (flow[e.e.id][i] - flow[i][e.e.id]));
                q.add(new Edge(new Vertex(e.e.id), new Vertex(i), c));
            }
        }
        
        return null;
    }
}
