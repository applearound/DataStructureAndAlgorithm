package me.zyz.dsal.collection.heap;

/**
 * @author yezhou
 */
public interface Heap<E extends Comparable<E>> {
    void enter(E e);

    E quit();

    E peek(int i);

    static <E extends Comparable<E>> MaxArrayHeap<E> maxHeap(E[] arr) {
        MaxArrayHeap<E> maxHeap = new MaxArrayHeap<>(arr);



        return maxHeap;
    }

    static <E extends Comparable<E>> MinArrayHeap<E> minHeap(E[] arr) {
        MinArrayHeap<E> minHeap = new MinArrayHeap<>(arr);

        return minHeap;
    }
}
