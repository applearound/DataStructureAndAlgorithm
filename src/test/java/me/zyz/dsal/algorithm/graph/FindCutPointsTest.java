package me.zyz.dsal.algorithm.graph;

import lombok.extern.slf4j.Slf4j;
import me.zyz.dsal.collection.graph.AdjacencyList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
class FindCutPointsTest {
    @Test
    void findBridge() throws IOException {
        try (final InputStream resourceStream = ClassLoader.getSystemResourceAsStream("findBridge.txt")) {
            Assertions.assertNotNull(resourceStream);

            final AdjacencyList adjacencyList = AdjacencyList.fromInputStream(resourceStream);
            final FindCutPoints findCutPoints = new FindCutPoints(adjacencyList);

            log.debug("{}", findCutPoints.result());
        } catch (final IOException e) {
            log.error("Read graph file error.", e);
            throw e;
        }
    }

    @Test
    void findBridge2() throws IOException {
        try (final InputStream resourceStream = ClassLoader.getSystemResourceAsStream("findBridge2.txt")) {
            Assertions.assertNotNull(resourceStream);

            final AdjacencyList adjacencyList = AdjacencyList.fromInputStream(resourceStream);
            final FindCutPoints findCutPoints = new FindCutPoints(adjacencyList);

            log.debug("{}", findCutPoints.result());
        } catch (final IOException e) {
            log.error("Read graph file error.", e);
            throw e;
        }
    }

    @Test
    void findBridge3() throws IOException {
        try (final InputStream resourceStream = ClassLoader.getSystemResourceAsStream("findBridge3.txt")) {
            Assertions.assertNotNull(resourceStream);

            final AdjacencyList adjacencyList = AdjacencyList.fromInputStream(resourceStream);
            final FindCutPoints findCutPoints = new FindCutPoints(adjacencyList);

            log.debug("{}", findCutPoints.result());
        } catch (final IOException e) {
            log.error("Read graph file error.", e);
            throw e;
        }
    }
}