package me.zyz.dsal.algorithm.sort.comparable;

import me.zyz.dsal.algorithm.sort.AbstractComparableArraySort;
import me.zyz.dsal.algorithm.sort.ArraySort;

import java.lang.reflect.Array;

/**
 * @author yz
 */
public class HeapArraySort extends AbstractComparableArraySort implements ArraySort {
    @Override
    protected void sort0(final Object array, final int startIdx, final int length) {
        heapSort0(array, 0, Array.getLength(array));
    }

    private void heapSort0(final Object array, final int startIdx, final int length) {
        heapify(array, startIdx, length);

        int currentSize = length;
        while (currentSize > 1) {
            final Object theMax = Array.get(array, startIdx);
            Array.set(array, startIdx, Array.get(array, --currentSize));
            Array.set(array, currentSize, theMax);

            diveDown(array, startIdx, startIdx + ((currentSize >> 1) - 1), currentSize);
        }
    }

    private void heapify(final Object arr, final int startIdx, final int length) {
        // 元素数量为0, 1的数组不需要堆化
        if (length < 2) {
            return;
        }

        int lastNonLeafPos = startIdx + ((length >> 1) - 1);
        for (int currentAdjustPos = lastNonLeafPos; currentAdjustPos >= startIdx; --currentAdjustPos) {
            diveDown(arr, currentAdjustPos, lastNonLeafPos, length);
        }
    }

    private void diveDown(final Object arr, final int pos, final int lastNonLeafPos, final int actualSize) {
        int currentPos = pos;
        while (currentPos <= lastNonLeafPos) {
            final Object parentValue = Array.get(arr, currentPos);

            // 和根比较的孩子，暂定为左
            int exchangePos = (currentPos << 1) + 1;
            Object exchangeValue = Array.get(arr, exchangePos);

            // 右孩子，需要判断是否存在
            int rightChildPos = (currentPos << 1) + 2;
            if (rightChildPos < actualSize) {
                final Object rightChildValue = Array.get(arr, rightChildPos);
                if (compare(rightChildValue, exchangeValue) > 0) {
                    exchangePos = rightChildPos;
                    exchangeValue = rightChildValue;
                }
            }

            if (compare(parentValue, exchangeValue) < 0) {
                Array.set(arr, currentPos, exchangeValue);
                Array.set(arr, exchangePos, parentValue);
                currentPos = exchangePos;
            } else {
                break;
            }
        }
    }
}
