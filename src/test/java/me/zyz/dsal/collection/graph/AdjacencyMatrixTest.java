package me.zyz.dsal.collection.graph;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.Objects;

@Slf4j
class AdjacencyMatrixTest {
    @Test
    void test() {
        final URL resource = this.getClass().getClassLoader().getResource("graph.txt");

        assert Objects.nonNull(resource);

        final AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix(resource.getPath());
        log.debug("adjacencyMatrix {}", adjacencyMatrix);
    }
}