package me.zyz.dsal.algorithm.graph;

import me.zyz.dsal.collection.graph.AdjacencyList;

import java.util.Arrays;

public class BiPartitionDetection {
    private final AdjacencyList graph;
    private final boolean[] visited;
    private int[] colors;
    private boolean isBiPartition = true;

    public BiPartitionDetection(final AdjacencyList graph) {
        this.graph = graph;
        this.visited = new boolean[graph.vertex()];
        this.colors = new int[graph.vertex()];
        Arrays.fill(this.colors, -1);

        // 时间复杂度 O(V + E)
        for (int i = 0; i < graph.vertex(); i++) {
            if (!visited[i]) {
                if (!dfs(i, 0)) {
                    isBiPartition = false;
                    break;
                }
            }
        }
    }

    public boolean isBiPartition() {
        return isBiPartition;
    }

    private boolean dfs(final int v, final int color) {
        visited[v] = true;
        colors[v] = color;
        for (final Integer w : graph.adjList(v)) {
            if (!visited[w]) {
                if (!dfs(w, 1 - color)) {
                    return false;
                }
            } else if (colors[w] == colors[v]) {
                return false;
            }
        }
        return true;
    }
}
