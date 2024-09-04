package me.zyz.dsal.collection.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class AdjacencyMatrix {
    private final int vertex;
    private final int edge;
    private final int[][] adj;

    public AdjacencyMatrix(final String filePath) {
        try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            final String firstLine = bufferedReader.readLine();

            try (final Scanner scanner = new Scanner(firstLine)) {
                final int vertex = scanner.nextInt();
                if (vertex < 0) {
                    throw new RuntimeException("negative vertx");
                }
                this.vertex = vertex;

                final int edge = scanner.nextInt();
                if (edge < 0) {
                    throw new RuntimeException("negative edge");
                }
                this.edge = edge;
            }

            this.adj = new int[vertex][vertex];

            for (int i = 0; i < edge; i++) {
                final String edgeInfoLine = bufferedReader.readLine();

                try (final Scanner scanner = new Scanner(edgeInfoLine)) {
                    final int vertexFrom = validate(scanner.nextInt());
                    final int vertexTo = validate(scanner.nextInt());

                    if (vertexFrom == vertexTo) {
                        throw new IllegalArgumentException("Self loop");
                    }

                    if (adj[vertexFrom][vertexTo] == 1) {
                        throw new IllegalArgumentException("Parallel");
                    }

                    adj[vertexFrom][vertexTo] = 1;
                    adj[vertexTo][vertexFrom] = 1;
                }
            }
        } catch (final IOException e) {
            throw new RuntimeException("Open File Failed", e);
        } catch (final Exception e) {
            throw new RuntimeException("Parse Failed", e);
        }
    }

    private int validate(final int vertex) {
        if (vertex < 0 || vertex >= this.vertex) {
            throw new IllegalArgumentException("vertx");
        }

        return vertex;
    }

    public int vertex() {
        return vertex;
    }

    public int edge() {
        return edge;
    }

    public boolean hasEdge(final int from, final int to) {
        return adj[validate(from)][validate(to)] == 1;
    }

    public int[] adjArray(final int v) {
        return Arrays.stream(adj[validate(v)]).filter(i -> i == 1).toArray();
    }

    public List<Integer> adjList(final int v) {
        return Arrays.stream(adj[validate(v)]).filter(i -> i == 1).boxed().collect(Collectors.toList());
    }

    public Set<Integer> adjSet(final int v) {
        return Arrays.stream(adj[validate(v)]).filter(i -> i == 1).boxed().collect(Collectors.toSet());
    }

    public int degree(final int v) {
        return (int) Arrays.stream(adj[validate(v)]).filter(i -> i == 1).count();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append(String.format("V = %d, E = %d%n", vertex, edge));
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                sb.append(adj[i][j]);
                if (j != vertex - 1) {
                    sb.append(' ');
                }
            }
            sb.append('\n');
        }

        return sb.toString();
    }
}
