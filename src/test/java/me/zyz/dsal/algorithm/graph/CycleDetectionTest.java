package me.zyz.dsal.algorithm.graph;

import lombok.extern.slf4j.Slf4j;
import me.zyz.dsal.collection.graph.AdjacencyList;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.Objects;

@Slf4j
class CycleDetectionTest {
    @Test
    void hasCycle() {
        final URL resource = this.getClass().getClassLoader().getResource("g2.txt");

        assert Objects.nonNull(resource);

        final AdjacencyList adjacencyList = new AdjacencyList(resource.getPath());
        final CycleDetection cycleDetection = new CycleDetection(adjacencyList);

        log.debug("{}", cycleDetection.hasCycle());
    }
}