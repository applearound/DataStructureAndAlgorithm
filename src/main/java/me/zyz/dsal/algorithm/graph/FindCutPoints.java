package me.zyz.dsal.algorithm.graph;

import me.zyz.dsal.collection.graph.AdjacencyList;

import java.util.HashSet;
import java.util.Set;

public class FindCutPoints {
    private final AdjacencyList graph;
    private final boolean[] visited;
    private final int[] ord;
    private final int[] low;
    private int cnt;

    private Set<Integer> res;

    public FindCutPoints(final AdjacencyList graph) {
        this.graph = graph;
        this.visited = new boolean[graph.vertex()];
        this.ord = new int[graph.vertex()];
        this.low = new int[graph.vertex()];
        this.cnt = 0;
        this.res = new HashSet<>();

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
        ord[v] = cnt;
        low[v] = ord[v];
        cnt++;

        int child = 0;
        for (final Integer w : graph.adjList(v)) {
            if (!visited[w]) {
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);
                if (v != parent && low[w] >= ord[v]) {
                    res.add(v);
                }

                if (v == parent) {
                    child++;
                    if (child > 1) {
                        res.add(v);
                    }
                }
            } else if (w != parent) {
                low[v] = Math.min(low[v], ord[w]);
            }
        }
    }

    public Iterable<Integer> result() {
        return res;
    }
}
