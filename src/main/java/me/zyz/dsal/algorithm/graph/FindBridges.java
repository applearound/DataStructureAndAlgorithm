package me.zyz.dsal.algorithm.graph;

import me.zyz.dsal.collection.graph.AdjacencyList;

import java.util.ArrayList;
import java.util.List;

public class FindBridges {
    private final AdjacencyList graph;
    private final boolean[] visited;
    private final int[] ord;
    private final int[] low;
    private int cnt;

    private List<Edge> res;

    public static class Edge {
        private final int v;
        private final int w;

        public Edge(final int v, final int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "v=" + v +
                    ", w=" + w +
                    '}';
        }
    }

    public FindBridges(final AdjacencyList graph) {
        this.graph = graph;
        this.visited = new boolean[graph.vertex()];
        this.ord = new int[graph.vertex()];
        this.low = new int[graph.vertex()];
        this.cnt = 0;
        this.res = new ArrayList<>();

        // 时间复杂度 O(V + E)
        for (int v = 0; v < graph.vertex(); v++) {
            if (visited[v]) {
                continue;
            }

            dfs(v, v);
        }
    }

    private void dfs(final int v, final int parent) {
        visited[v] = true;
        low[v] = ord[v] = cnt++;

        for (final Integer w : graph.adjList(v)) {
            if (!visited[w]) {
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);
                if (low[w] > ord[v]) {
                    res.add(new Edge(v, w));
                }
            } else if (w != parent) {
                low[v] = Math.min(low[v], ord[w]);
            }
        }
    }

    public Iterable<Edge> result() {
        return res;
    }
}
