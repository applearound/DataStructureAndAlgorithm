package me.zyz.dsal.collection.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class TestPerformanceTest {
    static Stream<Arguments> listProvider() {
        return Stream.of(
                Arguments.of(Named.of("ArrayList", new ArrayList<>())),
                Arguments.of(Named.of("LinkedList", new LinkedList<>()))
        );
    }

    @MethodSource(value = "listProvider")
    @ParameterizedTest(name = "{index} - {0}")
    void testAdd(final List<Integer> testList) {
        for (int i = 0; i < 100000; i++) {
            testList.add(i);
        }

        Assertions.assertEquals(100000, testList.size());

        for (int i = 0; i < 100000; i++) {
            Assertions.assertEquals(i, testList.get(i));
        }
    }
}
