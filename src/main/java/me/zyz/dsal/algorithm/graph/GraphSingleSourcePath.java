package me.zyz.dsal.algorithm.graph;

import me.zyz.dsal.collection.graph.AdjacencyList;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

public class GraphSingleSourcePath {
    private final AdjacencyList graph;
    private final boolean[] visited;
    private final int[] pre;
    private final int source;

    public GraphSingleSourcePath(final AdjacencyList graph, final int source) {
        if (!Objects.requireNonNull(graph).contains(source)) {
            throw new RuntimeException();
        }

        this.graph = graph;
        this.source = source;
        this.visited = new boolean[graph.vertex()];
        this.pre = new int[graph.vertex()];
        Arrays.fill(this.pre, -1);

        dfs(source, source);
    }

    private void dfs(final int v, final int parent) {
        visited[v] = true;
        pre[v] = parent;
        for (final Integer w : graph.adjList(v)) {
            if (!visited[w]) {
                dfs(w, v);
            }
        }
    }

    public boolean isConnectedTo(final int t) {
        if (!graph.contains(t)) {
            throw new RuntimeException();
        }

        return visited[t];
    }

    public Iterable<Integer> path(final int t) {
        if (!isConnectedTo(t)) {
            return null;
        }

        final LinkedList<Integer> result = new LinkedList<>();
        int cur = t;
        while (cur != source) {
            result.addFirst(cur);
            cur = pre[cur];
        }
        result.addFirst(source);

        return result;
    }
}
