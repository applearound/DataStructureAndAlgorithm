package me.zyz.dsal.algorithm.sort;

/**
 * @author yz
 */
public class HeapSort<E extends Comparable<E>> implements Sort<E> {

    @Override
    public void sort(E[] arr) {
        Heap<E> heap = new MaxHeap(arr.length);

        for (E e : arr) {
            heap.enter(e);
        }

        for (int i = arr.length - 1; i >= 0; --i) {
            arr[i] = heap.quit();
        }
    }

    public static void main(String[] args) {
        Integer[] integers = {5, 4, 3, 2, 1};
        new HeapSort<Integer>().sort(integers);

        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }
}
