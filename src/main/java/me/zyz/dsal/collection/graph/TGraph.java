package me.zyz.dsal.collection.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author yz
 */
public class TGraph {
    public static class Vertex {
        private String id;

        public Vertex(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Vertex vertex = (Vertex) o;
            return Objects.equals(id, vertex.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public static class VertexNode {
        private Vertex vertex;
        private List<Vertex> edges;

        public VertexNode(Vertex vertex) {
            this.vertex = vertex;
            this.edges = new LinkedList<>();
        }

        public void addEdge(Vertex vertex) {
            edges.add(vertex);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            VertexNode that = (VertexNode) o;
            return Objects.equals(vertex, that.vertex);
        }

        @Override
        public int hashCode() {
            return Objects.hash(vertex);
        }
    }

    private Set<VertexNode> vertexTableSet;
}
