package me.zyz.dsal.algorithm.sort;

import me.zyz.dsal.list.ArrayList;

import java.lang.reflect.Array;

/**
 * @author yz
 */
public class MergeSort extends AbstractSort {

    @Override
    public <E extends Comparable<E>> void sort(E[] arr) {
        recursionSort(arr);
    }

    /**
     * 使用递归的归并排序，同时也是自顶向下的归并排序
     *
     * @param arr 被排序数组
     * @param <E> Comparable
     */
    public <E extends Comparable<E>> void recursionSort(E[] arr) {
        recursionSort0(arr, 0, arr.length - 1);
    }

    /**
     * 自底向上的归并排序
     *
     * @param arr 被排序数组
     * @param <E> Comparable
     */
    public <E extends Comparable<E>> void nonRecursionSort(E[] arr) {
        nonRecursionSort0(arr, arr.length - 1);
    }

    public <E extends Comparable<E>> void nonRecursionSort0(E[] arr, int n) {
        for (int sz = 1; sz <= n; sz += sz) {
            for (int i = 0; i + sz < n; i += sz + sz) {
                merge(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1));
            }
        }
    }

    /**
     * 对 arr[l, h] 的范围进行排序
     *
     * @param arr 被排序数组
     * @param l   下界（包含）
     * @param h   上届（包含）
     * @param <E> Comparable
     */
    private <E extends Comparable<E>> void recursionSort0(E[] arr, int l, int h) {
        // 当a[l...h]区间足够小时，可以使用其他排序方法以便获得更好的性能
        if (h - l < 16) {
            insertionSort(arr, l, h);
            return;
        }

        // 当 l 和 h 较大时，(l + h) / 2 可能会产生溢出导致不希望的结果
        int mid = l + (h - l) / 2;
        recursionSort0(arr, l, mid);
        recursionSort0(arr, mid + 1, h);

        // 当arr[mid] <= arr[mid + 1]时，由于递归的特性，arr[l..h]完全有序，不需要进行归并
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge(arr, l, mid, h);
        }
    }

    /**
     * 对 arr[l...mid] 和 arr[mid+1...h] 两部分进行归并
     *
     * @param arr 被排序数组
     * @param l   下界（包含）
     * @param mid 下届数组的上届（包含）
     * @param h   上届（包含）
     * @param <E> Comparable
     */
    private <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int h) {
        E[] aux = (E[]) Array.newInstance(arr.getClass().getComponentType(), h - l + 1);

        // 初始化辅助数组aux
        for (int i = l; i <= h; ++i) {
            aux[i - l] = arr[i];
        }

        int i = l;
        int j = mid + 1;
        // k代表的是原数组arr上当前的待填元素下标
        for (int k = l; k <= h; ++k) {
            // 必然是 i 先到 mid 或者 j 先到 h，两种情况之一
            // 不存在 i 和 j 同时到达 mid 和 h

            // 如果i指针已经超过mid，则说明[l...mid]已经遍历完毕
            if (i > mid) {
                arr[k] = aux[j - l];
                j++;
                continue;
            }

            // 如果j指针已经超过h，则说明[mid+1...h]已经遍历完毕
            if (j > h) {
                arr[k] = aux[i - l];
                i++;
                continue;
            }

            // 比较两个数组对应位置下标，填入k
            if (aux[i - l].compareTo(aux[j - l]) <= 0) {
                arr[k] = aux[i - l];
                j++;
            } else {
                arr[k] = aux[j - l];
                i++;
            }
        }
    }

    /**
     * 用于小规模范围的插入排序
     *
     * @param arr 被排序数组
     * @param l   上界（包含）
     * @param h   下界（包含）
     * @param <E> Comparable
     */
    private <E extends Comparable<E>> void insertionSort(E[] arr, int l, int h) {
        for (int i = l + 1; i <= h; i++) {
            E e = arr[i];
            int j;
            for (j = i; j > l && e.compareTo(arr[j - 1]) < 0; --j) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }

    public static void main(String[] args) {
        TestUtil testUtil = TestUtil.getInstance();
        for (int i = 0; i < 100; i++) {
            Integer[] integers = testUtil.randomIntegerArray(1000, 1000);
            testUtil.test(integers, new MergeSort());
        }
    }
}
