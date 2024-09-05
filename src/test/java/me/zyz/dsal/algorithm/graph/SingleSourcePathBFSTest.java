package me.zyz.dsal.algorithm.graph;

import lombok.extern.slf4j.Slf4j;
import me.zyz.dsal.collection.graph.AdjacencyList;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.Objects;

@Slf4j
class SingleSourcePathBFSTest {
    @Test
    void path() {
        final URL resource = this.getClass().getClassLoader().getResource("graph.txt");

        assert Objects.nonNull(resource);

        final AdjacencyList adjacencyList = new AdjacencyList(resource.getPath());
        final SingleSourcePathBFS singleSourcePathBFS = new SingleSourcePathBFS(adjacencyList, 0);

        log.debug("{}", singleSourcePathBFS.isConnectedTo(6));
        log.debug("{}", singleSourcePathBFS.distance(6));
        log.debug("{}", singleSourcePathBFS.path(6));
    }
}