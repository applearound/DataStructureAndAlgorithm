package me.zyz.dsal.algorithm.sort;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author yz
 */
public class BucketSort implements Sort<Integer> {

    @Override
    public void sort(Integer[] arr) {
        Deque<Integer>[] deques = new Deque[10];
        for (int i = 0; i < deques.length; i++) {
            deques[i] = new LinkedList<>();
        }
        int maxValue = arr[0];
        for (Integer integer : arr) {
            if (integer > maxValue) {
                maxValue = integer;
            }
        }
        int maxLength = String.valueOf(maxValue).length();

        int divider = 1;
        for (int i = 0; i < maxLength; i++) {
            divider *= 10;
            for (Integer aLong : arr) {
                deques[aLong % divider].add(aLong);
            }

            int j = 0;
            for (Deque<Integer> deque : deques) {
                while (!deque.isEmpty()) {
                    arr[j++] = deque.pop();
                }
            }
        }

        for (Integer integer : arr) {
            System.out.println(integer);
        }
    }
}
