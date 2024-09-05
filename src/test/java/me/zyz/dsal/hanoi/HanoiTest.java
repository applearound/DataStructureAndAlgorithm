package me.zyz.dsal.hanoi;

import lombok.extern.slf4j.Slf4j;
import me.zyz.dsal.algorithm.hanoi.Hanoi;
import me.zyz.dsal.algorithm.hanoi.HanoiClassic;
import me.zyz.dsal.algorithm.hanoi.HanoiMachine;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class HanoiTest {

    static Stream<Arguments> hanoiProvider() {
        return Stream.of(
                Arguments.of(new HanoiClassic(3, "A", "C", "B"), 7),
                Arguments.of(new HanoiMachine(3, "A", "C", "B"), 7)
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
