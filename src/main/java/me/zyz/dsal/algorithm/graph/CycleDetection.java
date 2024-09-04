package me.zyz.dsal.algorithm.graph;

import me.zyz.dsal.collection.graph.AdjacencyList;

public class CycleDetection {
    private final AdjacencyList graph;
    private final boolean[] visited;
    private boolean hasCycle = false;

    public CycleDetection(final AdjacencyList graph) {
        this.graph = graph;
        this.visited = new boolean[graph.vertex()];

        // 时间复杂度 O(V + E)
        for (int i = 0; i < graph.vertex(); i++) {
            if (!visited[i]) {
                if (dfs(i, i)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    private boolean dfs(final int v, final int parent) {
        visited[v] = true;
        for (final Integer w : graph.adjList(v)) {
            if (!visited[w]) {
                if(dfs(w, v)) {
                    return true;
                }
            } else if (w != parent) {
                hasCycle = true;
                return true;
            }
        }
        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }
}
