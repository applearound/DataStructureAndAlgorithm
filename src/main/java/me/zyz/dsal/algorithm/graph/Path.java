package me.zyz.dsal.algorithm.graph;

import me.zyz.dsal.collection.graph.AdjacencyList;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

public class Path {
    private final AdjacencyList graph;
    private final boolean[] visited;
    private final int[] pre;
    private final int source;
    private final int target;

    public Path(final AdjacencyList graph, final int source, final int target) {
        Objects.requireNonNull(graph);
        if (!graph.contains(source) || !graph.contains(target)) {
            throw new RuntimeException();
        }

        this.graph = graph;
        this.source = source;
        this.target = target;
        this.visited = new boolean[graph.vertex()];
        this.pre = new int[graph.vertex()];
        Arrays.fill(this.pre, -1);

        dfs(source, source);
    }

    private boolean dfs(final int v, final int parent) {
        visited[v] = true;
        pre[v] = parent;

        if (v == target) {
            return true;
        }

        for (final Integer w : graph.adjList(v)) {
            if (!visited[w]) {
                if (dfs(w, v)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isConnectedTo() {
        return visited[target];
    }

    public Iterable<Integer> path() {
        if (!isConnectedTo()) {
            return null;
        }

        final LinkedList<Integer> result = new LinkedList<>();
        int cur = target;
        while (cur != source) {
            result.addFirst(cur);
            cur = pre[cur];
        }
        result.addFirst(source);

        return result;
    }
}
