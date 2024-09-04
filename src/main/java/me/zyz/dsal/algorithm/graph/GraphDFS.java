package me.zyz.dsal.algorithm.graph;

import me.zyz.dsal.collection.graph.AdjacencyList;

import java.util.ArrayList;
import java.util.List;

public class GraphDFS {
    private final List<Integer> pre = new ArrayList<>();
    private final List<Integer> post = new ArrayList<>();
    private final AdjacencyList graph;
    private final boolean[] visited;

    public GraphDFS(final AdjacencyList graph) {
        this.graph = graph;
        this.visited = new boolean[graph.vertex()];

        // 时间复杂度 O(V + E)
        for (int i = 0; i < graph.vertex(); i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }

    private void dfs(final int v) {
        visited[v] = true;
        pre.add(v);
        for (final Integer w : graph.adjList(v)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
        post.add(v);
    }

    public Iterable<Integer> order() {
        return pre;
    }

    public Iterable<Integer> postOrder() {
        return post;
    }
}
