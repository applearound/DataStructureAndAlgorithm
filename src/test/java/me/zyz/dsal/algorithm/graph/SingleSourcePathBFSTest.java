package me.zyz.dsal.algorithm.graph;

import lombok.extern.slf4j.Slf4j;
import me.zyz.dsal.collection.graph.AdjacencyList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
class SingleSourcePathBFSTest {
    @Test
    void path() throws IOException {
        try (final InputStream resourceStream = ClassLoader.getSystemResourceAsStream("graph.txt")) {
            Assertions.assertNotNull(resourceStream);

            final AdjacencyList       adjacencyList       = AdjacencyList.fromInputStream(resourceStream);
            final SingleSourcePathBFS singleSourcePathBFS = new SingleSourcePathBFS(adjacencyList, 0);

            log.debug("{}", singleSourcePathBFS.isConnectedTo(6));
            log.debug("{}", singleSourcePathBFS.distance(6));
            log.debug("{}", singleSourcePathBFS.path(6));
        } catch (final IOException e) {
            log.error("Read graph file error.", e);
            throw e;
        }
    }
}