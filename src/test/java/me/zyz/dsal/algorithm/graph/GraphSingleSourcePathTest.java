package me.zyz.dsal.algorithm.graph;

import lombok.extern.slf4j.Slf4j;
import me.zyz.dsal.collection.graph.AdjacencyList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
class GraphSingleSourcePathTest {
    @Test
    void path() throws IOException {
        try (final InputStream resourceStream = ClassLoader.getSystemResourceAsStream("graph.txt")) {
            Assertions.assertNotNull(resourceStream);

            final AdjacencyList         adjacencyList         = AdjacencyList.fromInputStream(resourceStream);
            final GraphSingleSourcePath graphSingleSourcePath = new GraphSingleSourcePath(adjacencyList, 0);

            log.debug("{}", graphSingleSourcePath.path(6));
        } catch (final IOException e) {
            log.error("Read graph file error.", e);
            throw e;
        }
    }

    @Test
    void path2() throws IOException {
        try (final InputStream resourceStream = ClassLoader.getSystemResourceAsStream("graph.txt")) {
            Assertions.assertNotNull(resourceStream);

            final AdjacencyList adjacencyList         = AdjacencyList.fromInputStream(resourceStream);
            final Path          graphSingleSourcePath = new Path(adjacencyList, 0, 5);

            log.debug("{}", graphSingleSourcePath.path());
        } catch (final IOException e) {
            log.error("Read graph file error.", e);
            throw e;
        }
    }
}