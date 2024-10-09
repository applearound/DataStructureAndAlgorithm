package me.zyz.dsal.algorithm.graph;

import lombok.extern.slf4j.Slf4j;
import me.zyz.dsal.collection.graph.AdjacencyList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
class GraphCCTest {
    @Test
    void count() throws IOException {
        try (final InputStream resourceStream = ClassLoader.getSystemResourceAsStream("graph.txt")) {
            Assertions.assertNotNull(resourceStream);

            final AdjacencyList adjacencyList = AdjacencyList.fromInputStream(resourceStream);
            final GraphCC       graphCC       = new GraphCC(adjacencyList);

            log.debug("{}", graphCC.count());
        } catch (final IOException e) {
            log.error("Read graph file error.", e);
            throw e;
        }
    }

    @Test
    void isConnected() throws IOException {
        try (final InputStream resourceStream = ClassLoader.getSystemResourceAsStream("graph.txt")) {
            Assertions.assertNotNull(resourceStream);

            final AdjacencyList adjacencyList = AdjacencyList.fromInputStream(resourceStream);
            final GraphCC       graphCC       = new GraphCC(adjacencyList);

            log.debug("{}", graphCC.isConnected(0, 5));
        } catch (final IOException e) {
            log.error("Read graph file error.", e);
            throw e;
        }
    }

    @Test
    void components() throws IOException {
        try (final InputStream resourceStream = ClassLoader.getSystemResourceAsStream("graph.txt")) {
            Assertions.assertNotNull(resourceStream);

            final AdjacencyList adjacencyList = AdjacencyList.fromInputStream(resourceStream);
            final GraphCC       graphCC       = new GraphCC(adjacencyList);

            for (final List<Integer> component : graphCC.components()) {
                log.debug("{}", component);
            }
        } catch (final IOException e) {
            log.error("Read graph file error.", e);
            throw e;
        }
    }
}