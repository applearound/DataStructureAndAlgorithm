package me.zyz.dsal.algorithm.hanoi;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class HanoiTest {

    static Stream<Arguments> hanoiProvider() {
        return IntStream.range(1, 10)
                .mapToObj(i ->
                        Arguments.of(new HanoiClassic(i, "A", "C", "B"), (1 << i) - 1)
                );
    }

    @ParameterizedTest
    @MethodSource("hanoiProvider")
    void testHanoi(final Hanoi hanoi, final int moveCount) {
        final int count = hanoi.move();

        log.info("{} 测试类共需要移动 {} 次", hanoi.getClass().getSimpleName(), count);

        assertEquals(moveCount, count);
    }
}
