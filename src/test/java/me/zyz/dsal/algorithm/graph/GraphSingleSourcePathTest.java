package me.zyz.dsal.algorithm.graph;

import lombok.extern.slf4j.Slf4j;
import me.zyz.dsal.collection.graph.AdjacencyList;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class GraphSingleSourcePathTest {
    @Test
    void path() {
        final URL resource = this.getClass().getClassLoader().getResource("graph.txt");

        assert Objects.nonNull(resource);

        final AdjacencyList adjacencyList = new AdjacencyList(resource.getPath());
        final GraphSingleSourcePath graphSingleSourcePath = new GraphSingleSourcePath(adjacencyList, 0);

        log.debug("{}", graphSingleSourcePath.path(6));
    }

    @Test
    void path2() {
        final URL resource = this.getClass().getClassLoader().getResource("graph.txt");

        assert Objects.nonNull(resource);

        final AdjacencyList adjacencyList = new AdjacencyList(resource.getPath());
        final Path graphSingleSourcePath = new Path(adjacencyList, 0, 5);

        log.debug("{}", graphSingleSourcePath.path());
    }
}