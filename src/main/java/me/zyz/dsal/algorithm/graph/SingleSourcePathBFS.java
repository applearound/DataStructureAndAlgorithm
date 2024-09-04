package me.zyz.dsal.algorithm.graph;

import me.zyz.dsal.collection.graph.AdjacencyList;

import java.util.*;

public class SingleSourcePathBFS {
    private final AdjacencyList graph;
    private final boolean[] visited;
    private final int source;
    private final int[] pre;
    private final int[] distance;

    public SingleSourcePathBFS(final AdjacencyList graph, final int source) {
        this.graph = graph;
        this.visited = new boolean[graph.vertex()];
        this.source = source;
        this.pre = new int[graph.vertex()];
        this.distance = new int[graph.vertex()];
        Arrays.fill(pre, -1);
        Arrays.fill(distance, -1);

        bfs(source);
    }

    private void bfs(final int v) {
        final Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;
        pre[v] = v;
        distance[v] = 0;

        while (!queue.isEmpty()) {
            int vertex = queue.remove();

            for (int w : graph.adjList(vertex)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = vertex;
                    distance[w] = distance[vertex] + 1;
                }
            }
        }
    }

    public boolean isConnectedTo(final int target) {
        if (!graph.contains(target)) {
            throw new RuntimeException();
        }

        return visited[target];
    }

    public int distance(final int target) {
        isConnectedTo(target);

        return distance[target];
    }

    public Iterable<Integer> path(final int target) {
        if (!isConnectedTo(target)) {
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
