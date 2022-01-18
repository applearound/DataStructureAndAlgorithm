package me.zyz.dsal.collection.list;

/**
 * @author yezhou
 */
public interface Deque<E> {

    void addFirst(E e);


    void addLast(E e);


    boolean offerFirst(E e);


    boolean offerLast(E e);


    E removeFirst();


    E removeLast();


    E pollFirst();


    E pollLast();


    E getFirst();


    E getLast();


    E peekFirst();


    E peekLast();


    boolean removeFirstOccurrence(Object o);


    boolean removeLastOccurrence(Object o);


    boolean add(E e);


    boolean offer(E e);


    E remove();


    E poll();


    E element();


    E peek();


    void push(E e);


    E pop();


    boolean remove(Object o);


    boolean contains(Object o);


    int size();
}
