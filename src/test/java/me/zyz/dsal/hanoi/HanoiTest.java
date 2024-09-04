package me.zyz.dsal.hanoi;

import lombok.extern.slf4j.Slf4j;
import me.zyz.dsal.algorithm.hanoi.HanoiClassic;
import me.zyz.dsal.algorithm.hanoi.HanoiMachine;
import org.junit.jupiter.api.Test;

@Slf4j
class HanoiTest {
    @Test
    void testClassic() {
        final HanoiClassic hanoiClassic = new HanoiClassic();

        final int count = hanoiClassic.move(3, "A", "B", "C");
        log.info("共需要移动 {} 次", count);
    }

    @Test
    void testHanoiMachine() {
        final HanoiMachine hanoiMachine = new HanoiMachine(3, "A", "B", "C");

        final int count = hanoiMachine.move();
        log.info("共需要移动 {} 次", count);
    }
}
