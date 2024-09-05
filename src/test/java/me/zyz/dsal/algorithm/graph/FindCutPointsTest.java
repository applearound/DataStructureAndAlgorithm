package me.zyz.dsal.algorithm.graph;

import lombok.extern.slf4j.Slf4j;
import me.zyz.dsal.collection.graph.AdjacencyList;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.Objects;

@Slf4j
class FindCutPointsTest {
    @Test
    void findBridge() {
        final URL resource = this.getClass().getClassLoader().getResource("findBridge.txt");

        assert Objects.nonNull(resource);

        final AdjacencyList adjacencyList = new AdjacencyList(resource.getPath());
        final FindCutPoints findCutPoints = new FindCutPoints(adjacencyList);

        log.debug("{}", findCutPoints.result());
    }

    @Test
    void findBridge2() {
        final URL resource = this.getClass().getClassLoader().getResource("findBridge2.txt");

        assert Objects.nonNull(resource);

        final AdjacencyList adjacencyList = new AdjacencyList(resource.getPath());
        final FindCutPoints findCutPoints = new FindCutPoints(adjacencyList);

        log.debug("{}", findCutPoints.result());
    }

    @Test
    void findBridgeTree() {
        final URL resource = this.getClass().getClassLoader().getResource("findBridge3.txt");

        assert Objects.nonNull(resource);

        final AdjacencyList adjacencyList = new AdjacencyList(resource.getPath());
        final FindCutPoints findCutPoints = new FindCutPoints(adjacencyList);

        log.debug("{}", findCutPoints.result());
    }
}