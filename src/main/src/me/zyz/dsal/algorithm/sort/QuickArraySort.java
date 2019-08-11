package me.zyz.dsal.algorithm.sort;

import java.util.Random;

/**
 * @author yezhou
 */
public class QuickArraySort<E extends Comparable<E>> extends AbstractArraySort<E> {
    public enum Partitions {
        /**
         * 分区类型
         */
        DEFAULT,
        SINGLE_WAY,
        DOUBLE_WAY,
        TRIPLE_WAY
    }

    private final Partition<E> partition;

    public QuickArraySort() {
        this(Partitions.DEFAULT);
    }

    public QuickArraySort(Partitions partitions) {
        switch (partitions) {
            case SINGLE_WAY:
                this.partition = new SingleWayPartition<>();
                break;
            case DOUBLE_WAY:
                this.partition = new DoubleWayPartition<>();
                break;
            case TRIPLE_WAY:
            default:
                this.partition = new TripleWayPartition<>();
                break;
        }
    }

    @Override
    public void sort(E[] arr) {
        recursionSort(arr, 0, arr.length - 1);
    }

    public void recursionSort(E[] arr, int low, int high) {
        if (high - low < 16) {
            insertionSort(arr, low, high);
            return;
        }

        int p[] = partition.partition(arr, low, high);
        recursionSort(arr, low, p[0] - 1);
        recursionSort(arr, p[1] + 1, high);
    }

    private interface Partition<E extends Comparable<E>> {
        /**
         * 分割
         *
         * @param arr  待排序数组
         * @param low  下界（包含）
         * @param high 上届（包含）
         * @return pivot下标
         */
        int[] partition(E[] arr, int low, int high);
    }

    /**
     * 单路分割
     * 同时适用于链表
     */
    private static class SingleWayPartition<E extends Comparable<E>> implements Partition<E> {
        private final Random random = new Random();

        @Override
        public int[] partition(E[] arr, int low, int high) {
            swap(arr, low, low + random.nextInt(high - low + 1));
            E pivot = arr[low];

            // [areaP:p][areaLP:<p][areaGP:>=p][areaUR:unread]
            // 指向areaGP区的最低位指针
            int areaGtLow = low + 1;

            // i表示当前未进入分区的待排元素
            for (int i = low + 1; i <= high; ++i) {
                if (arr[i].compareTo(pivot) < 0) {
                    swap(arr, areaGtLow, i);
                    areaGtLow++;
                }
            }

            // pivot需要和areaLP区的最高位互换，areaLP区的最高位是p的最终位置
            swap(arr, low, areaGtLow - 1);

            return new int[]{areaGtLow - 1, areaGtLow - 1};
        }
    }

    private static class DoubleWayPartition<E extends Comparable<E>> implements Partition<E> {
        private final Random random = new Random();

        @Override
        public int[] partition(E[] arr, int low, int high) {
            swap(arr, low, low + random.nextInt(high - low + 1));
            E pivot = arr[low];

            // [p][areaLP:<=p][i][areaUR:unread][j][areaGP:>=p]
            int i = low;
            int j = high + 1;

            while (true) {
                while (arr[++i].compareTo(pivot) < 0 && i < high) {
                }

                while (arr[--j].compareTo(pivot) > 0 && j > low) {
                }
                if (i >= j) {
                    break;
                }
                swap(arr, i, j);
            }
            swap(arr, low, j);

            return new int[]{j, j};
        }
    }

    private static class TripleWayPartition<E extends Comparable<E>> implements Partition<E> {
        private final Random random = new Random();

        @Override
        public int[] partition(E[] arr, int low, int high) {
            swap(arr, low, low + random.nextInt(high - low + 1));
            E pivot = arr[low];

            // [p][areaLP:<p][areaEQ:=p][i][areaU:wqR:unread][areaGP:>p]
            int ltHigh = low;
            int gtLow = high + 1;

            int i = low + 1;
            while (i < gtLow) {
                if (arr[i].compareTo(pivot) < 0) {
                    swap(arr, i++, ++ltHigh);
                } else if (arr[i].compareTo(pivot) > 0) {
                    swap(arr, i, --gtLow);
                } else {
                    i++;
                }
            }
            swap(arr, low, ltHigh--);

            return new int[]{ltHigh + 1, gtLow - 1};
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
