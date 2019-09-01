package me.zyz.dsal.algorithm.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author yz
 */
public class MergeArraySort<E extends Comparable<E>> extends AbstractArraySort<E> {

    @Override
    public void sort(E[] arr) {
        recursionSort(arr, 0, arr.length - 1, (E[]) Array.newInstance(arr.getClass().getComponentType(), arr.length));
    }

    /**
     * 使用递归的归并排序，同时也是自顶向下的归并排序
     * 对 arr[l, h] 的范围进行排序
     *
     * @param arr 被排序数组
     * @param l   下界（包含）
     * @param h   上届（包含）
     */
    private void recursionSort(E[] arr, int l, int h, E[] aux) {
        // 当a[l...h]区间足够小时，可以使用其他排序方法以便获得更好的性能
        if (h - l < 16) {
            insertionSort(arr, l, h);
            return;
        }

        // 当 l 和 h 较大时，(l + h) / 2 可能会产生溢出导致不希望的结果
        int mid = l + (h - l) / 2;
        recursionSort(arr, l, mid, aux);
        recursionSort(arr, mid + 1, h, aux);

        // 当arr[mid] <= arr[mid + 1]时，由于递归的特性，arr[l..h]完全有序，不需要进行归并
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge(arr, l, mid, h, aux);
        }
    }

    /**
     * 自底向上的归并排序
     *
     * @param arr 被排序数组
     */
    public void nonRecursionSort(E[] arr, int n) {
        for (int sz = 1; sz <= n; sz += sz) {
            for (int i = 0; i + sz < n; i += sz + sz) {
                merge(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1), Arrays.copyOf(arr, arr.length));
            }
        }
    }

    /**
     * 对 arr[l...mid] 和 arr[mid+1...h] 两部分进行归并
     *
     * @param arr 被排序数组
     * @param l   下界（包含）
     * @param mid 下届数组的上届（包含）
     * @param h   上届（包含）
     */
    private void merge(E[] arr, int l, int mid, int h, E[] aux) {
        // 初始化辅助数组aux
        System.arraycopy(arr, l, aux, l, h - l + 1);

        int i = l;
        int j = mid + 1;
        // k代表的是原数组arr上当前的待填元素下标
        for (int k = l; k <= h; ++k) {
            // 必然是 i 先到 mid 或者 j 先到 h，两种情况之一
            // 不存在 i 和 j 同时到达 mid 和 h

            // 如果i指针已经超过mid，则说明[l...mid]已经遍历完毕
            if (i > mid) {
                arr[k] = aux[j++];
                continue;
            }

            // 如果j指针已经超过h，则说明[mid+1...h]已经遍历完毕
            if (j > h) {
                arr[k] = aux[i++];
                continue;
            }

            // 比较两个数组对应位置下标，填入k
            if (aux[i].compareTo(aux[j]) <= 0) {
                arr[k] = aux[i++];
            } else {
                arr[k] = aux[j++];
            }
        }
    }

    /**
     * 用于小规模范围的插入排序
     *
     * @param arr 被排序数组
     * @param l   上界（包含）
     * @param h   下界（包含）
     */
    private void insertionSort(E[] arr, int l, int h) {
        for (int i = l + 1; i <= h; i++) {
            E e = arr[i];
            int j;
            for (j = i; j > l && e.compareTo(arr[j - 1]) < 0; --j) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }
}
