package me.zyz.dsal.algorithm.graph;

import me.zyz.dsal.collection.graph.AdjacencyList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphCC {
    private final AdjacencyList graph;
    private final int[] visited;
    // 联通分量个数
    private int ccCount = 0;

    public GraphCC(final AdjacencyList graph) {
        this.graph = graph;
        this.visited = new int[graph.vertex()];
        Arrays.fill(this.visited, -1);

        // 时间复杂度 O(V + E)
        for (int i = 0; i < graph.vertex(); i++) {
            if (visited[i] == -1) {
                dfs(i, ccCount);
                ccCount += 1;
            }
        }
    }

    private void dfs(final int v, final int ccid) {
        visited[v] = ccid;
        for (final Integer w : graph.adjList(v)) {
            if (visited[w] == -1) {
                dfs(w, ccid);
            }
        }
    }

    public int count() {
        for (final int i : visited) {
            System.out.print(i + " ");
        }
        System.out.println();
        return ccCount;
    }

    public boolean isConnected(final int v, final int w) {
        if (!graph.contains(v) ||
                !graph.contains(w)) {
            throw new RuntimeException();
        }
        return visited[v] == visited[w];
    }

    public List<Integer>[] components() {
        final List<Integer>[] result = new ArrayList[ccCount];
        for (int i = 0; i < ccCount; i++) {
            result[i] = new ArrayList<>();
        }

        for (int v = 0; v < graph.vertex(); v++) {
            result[visited[v]].add(v);
        }

        return result;
    }
}
