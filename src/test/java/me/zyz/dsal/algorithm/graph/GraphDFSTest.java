package me.zyz.dsal.algorithm.graph;

import lombok.extern.slf4j.Slf4j;
import me.zyz.dsal.collection.graph.AdjacencyList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
class GraphDFSTest {
    @Test
    void order() throws IOException {
        try (final InputStream resourceStream = ClassLoader.getSystemResourceAsStream("graph.txt")) {
            Assertions.assertNotNull(resourceStream);

            final AdjacencyList adjacencyList = AdjacencyList.fromInputStream(resourceStream);
            final GraphDFS      graphDFS      = new GraphDFS(adjacencyList);

            log.debug("{}", graphDFS.order());
        } catch (final IOException e) {
            log.error("Read graph file error.", e);
            throw e;
        }
    }

    @Test
    void postOrder() throws IOException {
        try (final InputStream resourceStream = ClassLoader.getSystemResourceAsStream("graph.txt")) {
            Assertions.assertNotNull(resourceStream);

            final AdjacencyList adjacencyList = AdjacencyList.fromInputStream(resourceStream);
            final GraphDFS      graphDFS      = new GraphDFS(adjacencyList);

            log.debug("{}", graphDFS.postOrder());
        } catch (final IOException e) {
            log.error("Read graph file error.", e);
            throw e;
        }
    }
}