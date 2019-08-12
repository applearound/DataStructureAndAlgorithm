package me.zyz.dsal;

import me.zyz.dsal.collection.list.LinkedList;
import me.zyz.dsal.collection.list.Queue;
import me.zyz.dsal.collection.list.Stack;
import org.junit.jupiter.api.Test;

public class ProjectTest {
    @Test
    public void test() {
        Queue<Integer> stack = new LinkedList<>();
        stack.enter(1);
        stack.enter(2);
        stack.enter(3);
        stack.enter(4);

        while (!stack.isEmpty())
            System.out.println(stack.quit());
    }
}
