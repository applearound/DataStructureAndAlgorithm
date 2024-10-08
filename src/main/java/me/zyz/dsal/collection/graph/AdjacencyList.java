package me.zyz.dsal.collection.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class AdjacencyList {
    private final int            vertex;
    private final int            edge;
    private final Set<Integer>[] adj;

    /**
     * Build graph from disk file
     *
     * @param filePath file path
     */
    public AdjacencyList(final String filePath) {
        try (final BufferedReader bufferedReader =
                     new BufferedReader(new FileReader(filePath))) {
            final String firstLine = bufferedReader.readLine();

            try (final Scanner scanner = new Scanner(firstLine)) {
                final int vertex = scanner.nextInt();
                if (vertex < 0) {
                    throw new IllegalArgumentException("vertx");
                }
                this.vertex = vertex;

                final int edge = scanner.nextInt();
                if (edge < 0) {
                    throw new IllegalArgumentException("edge");
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
                    final int vertexTo   = validate(scanner.nextInt());

                    if (vertexFrom == vertexTo) {
                        throw new IllegalArgumentException("self loop detected: " + vertexFrom);
                    }

                    if (adj[vertexFrom].contains(vertexTo)) {
                        throw new IllegalArgumentException(String.format("parallel edge detected: %d to %d", vertexFrom, vertexTo));
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

    /**
     * Return if this graph contains vertex v
     *
     * @param v vertex
     * @return true if this graph contains vertex v, else false
     */
    public boolean contains(final int v) {
        return v >= 0 && v < vertex;
    }

    /**
     * Return the number of vertex
     *
     * @return the number of vertex
     */
    public int vertex() {
        return vertex;
    }

    /**
     * Return the number of edge
     *
     * @return the number of edge
     */
    public int edge() {
        return edge;
    }

    /**
     * Return if there is an edge from vertex from to vertex to
     *
     * @param from start vertex
     * @param to   target vertex
     * @return true if there is an edge from vertex from to vertex to, else false
     */
    public boolean hasEdge(final int from, final int to) {
        return adj[validate(from)].contains(validate(to));
    }

    /**
     * Return the adjacent vertexes of vertex v
     *
     * @param v vertex
     * @return the adjacent vertexes of vertex v
     */
    public Set<Integer> adjList(final int v) {
        return Collections.unmodifiableSet(adj[validate(v)]);
    }

    /**
     * Return the degree of vertex v
     *
     * @param v vertex
     * @return the degree of vertex v
     */
    public int degree(final int v) {
        return adj[validate(v)].size();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append(String.format("V = %d, E = %d\n", vertex, edge));
        for (int i = 0; i < vertex; i++) {
            sb.append(String.format("%d: ", i));
            sb.append(String.join(" ", adj[i].stream().map(String::valueOf).toList()));
            sb.append('\n');
        }

        return sb.toString();
    }
}
