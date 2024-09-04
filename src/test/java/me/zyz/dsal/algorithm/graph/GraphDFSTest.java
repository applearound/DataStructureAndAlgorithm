package me.zyz.dsal.algorithm.graph;

import lombok.extern.slf4j.Slf4j;
import me.zyz.dsal.collection.graph.AdjacencyList;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class GraphDFSTest {
    @Test
    void order() {
        final URL resource = this.getClass().getClassLoader().getResource("graph.txt");

        assert Objects.nonNull(resource);

        final AdjacencyList adjacencyList = new AdjacencyList(resource.getPath());
        final GraphDFS graphDFS = new GraphDFS(adjacencyList);

        log.debug("{}", graphDFS.order());
    }

    @Test
    void postOrder() {
        final URL resource = this.getClass().getClassLoader().getResource("graph.txt");

        assert Objects.nonNull(resource);

        final AdjacencyList adjacencyList = new AdjacencyList(resource.getPath());
        final GraphDFS graphDFS = new GraphDFS(adjacencyList);

        log.debug("{}", graphDFS.postOrder());
    }
}