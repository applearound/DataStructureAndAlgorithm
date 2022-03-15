package me.zyz.dsal.collection.graph;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.Objects;

@Slf4j
class AdjMatrixTest {
    @Test
    void test() {
        final URL resource = this.getClass().getClassLoader().getResource("graph.txt");

        assert Objects.nonNull(resource);

        final AdjMatrix adjMatrix = new AdjMatrix(resource.getPath());
        log.debug("matrix {}", adjMatrix);
    }
}