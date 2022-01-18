package me.zyz.dsal.algorithm.sort.comparable;

import me.zyz.dsal.algorithm.sort.AbstractComparableArraySort;
import me.zyz.dsal.algorithm.sort.ArraySort;

import java.lang.reflect.Array;

public class BubbleArraySort extends AbstractComparableArraySort implements ArraySort {
    @Override
    protected void sort0(final Object array, final int startIdx, final int endIdx) {
        // 表示是否发生过交换行为
        boolean flag;
        for (int i = endIdx; i > 0; --i) {
            flag = false;
            Object current = Array.get(array, startIdx);
            for (int j = startIdx; j < i; ++j) {
                final Object next = Array.get(array, j + 1);
                if (compare(current, next) > 0) {
                    Array.set(array, j, next);
                    Array.set(array, j + 1, current);
                    flag = true;
                } else {
                    current = next;
                }
            }

            // 如果当次循环未发生任何交换行为，则可以直接退出整个循环
            if (!flag) {
                break;
            }
        }
    }
}
