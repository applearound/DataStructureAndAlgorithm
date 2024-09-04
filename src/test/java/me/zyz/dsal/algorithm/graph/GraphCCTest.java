package me.zyz.dsal.algorithm.graph;

import lombok.extern.slf4j.Slf4j;
import me.zyz.dsal.collection.graph.AdjacencyList;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class GraphCCTest {
    @Test
    void count() {
            final URL resource = this.getClass().getClassLoader().getResource("graph.txt");

            assert Objects.nonNull(resource);

            final AdjacencyList adjacencyList = new AdjacencyList(resource.getPath());
            final GraphCC graphCC = new GraphCC(adjacencyList);

            log.debug("{}", graphCC.count());
    }

    @Test
    void isConnected() {
        final URL resource = this.getClass().getClassLoader().getResource("graph.txt");

        assert Objects.nonNull(resource);

        final AdjacencyList adjacencyList = new AdjacencyList(resource.getPath());
        final GraphCC graphCC = new GraphCC(adjacencyList);

        log.debug("{}", graphCC.isConnected(0, 5));
    }

    @Test
    void components() {
        final URL resource = this.getClass().getClassLoader().getResource("graph.txt");

        assert Objects.nonNull(resource);

        final AdjacencyList adjacencyList = new AdjacencyList(resource.getPath());
        final GraphCC graphCC = new GraphCC(adjacencyList);

        for (final List<Integer> component : graphCC.components()) {
            log.debug("{}", component);
        }
    }
}