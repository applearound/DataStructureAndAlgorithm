package me.zyz.dsal.collection.graph;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URL;

@Slf4j
class AdjacencyListTest {
    @Test
    void test() {
        final URL resource =
                ClassLoader.getSystemResource("graph.txt");

        Assertions.assertNotNull(resource);

        final AdjacencyList adjacencyList = new AdjacencyList(resource.getPath());

        log.debug("adjacencyList:\n{}", adjacencyList);
    }
}