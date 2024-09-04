package me.zyz.dsal.collection.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class AdjacencyList {
    private final int vertex;
    private final int edge;
    private final Set<Integer>[] adj;

    public AdjacencyList(final String filePath) {
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

            this.adj = new TreeSet[vertex];
            for (int i = 0; i < vertex; i++) {
                adj[i] = new TreeSet<>();
            }

            for (int i = 0; i < edge; i++) {
                final String edgeInfoLine = bufferedReader.readLine();

                try (final Scanner scanner = new Scanner(edgeInfoLine)) {
                    final int vertexFrom = validate(scanner.nextInt());
                    final int vertexTo = validate(scanner.nextInt());

                    if (vertexFrom == vertexTo) {
                        throw new IllegalArgumentException("Self loop");
                    }

                    if (adj[vertexFrom].contains(vertexTo)) {
                        throw new IllegalArgumentException("Parallel");
                    }

                    adj[vertexFrom].add(vertexTo);
                    adj[vertexTo].add(vertexFrom);
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

    public boolean contains(final int v) {
        return v >= 0 && v < vertex;
    }

    public int vertex() {
        return vertex;
    }

    public int edge() {
        return edge;
    }

    public boolean hasEdge(final int from, final int to) {
        return adj[validate(from)].contains(validate(to));
    }

    public Set<Integer> adjList(final int v) {
        return Collections.unmodifiableSet(adj[validate(v)]);
    }

    public int degree(final int v) {
        return adj[validate(v)].size();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append(String.format("V = %d, E = %d%n", vertex, edge));
        for (int i = 0; i < vertex; i++) {
            sb.append(String.format("%d : ", i));
            adj[i].forEach(e -> {
                sb.append(e);
                sb.append(' ');
            });
            sb.deleteCharAt(sb.length() - 1);
            sb.append('\n');
        }

        return sb.toString();
    }
}
