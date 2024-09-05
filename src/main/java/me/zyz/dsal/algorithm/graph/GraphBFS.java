package me.zyz.dsal.algorithm.graph;

import me.zyz.dsal.collection.graph.AdjacencyList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphBFS {
    private final AdjacencyList graph;
    private final boolean[] visited;

    private List<Integer> order = new ArrayList<>();

    public GraphBFS(final AdjacencyList graph) {
        this.graph = graph;
        this.visited = new boolean[graph.vertex()];

        // 时间复杂度 O(V + E)
        for (int i = 0; i < graph.vertex(); i++) {
            if (!visited[i]) {
                bfs(i);
            }
        }
    }

    private void bfs(final int v) {
        final Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            int vertex = queue.remove();
            order.add(vertex);

            for (int w : graph.adjList(vertex)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                }
            }
        }
    }

    public Iterable<Integer> order() {
        return order;
    }
}
