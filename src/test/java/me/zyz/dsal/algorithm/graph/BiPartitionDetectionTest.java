package me.zyz.dsal.algorithm.graph;

import lombok.extern.slf4j.Slf4j;
import me.zyz.dsal.collection.graph.AdjacencyList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;


@Slf4j
class BiPartitionDetectionTest {
    @Test
    void isBiPartition() throws IOException {
        try (final InputStream resourceStream = ClassLoader.getSystemResourceAsStream("g2.txt")) {
            Assertions.assertNotNull(resourceStream);

            final AdjacencyList        adjacencyList        = AdjacencyList.fromInputStream(resourceStream);
            final BiPartitionDetection biPartitionDetection = new BiPartitionDetection(adjacencyList);

            log.debug("{}", biPartitionDetection.isBiPartition());
        } catch (final IOException e) {
            log.error("Read graph file error.", e);
            throw e;
        }
    }
}