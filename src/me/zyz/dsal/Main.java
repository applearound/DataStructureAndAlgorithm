package me.zyz.dsal;

import me.zyz.dsal.list.LinkedList;


/**
 * @author zyz
 */
public class Main {

    public static void main(String[] args) {
        LinkedList<Integer> arrayList = new LinkedList<>();
        for (int i = 0; i < 10; ++i) {
            arrayList.add(i);
        }
        for (int i = 0; i < 10; ++i) {
            System.out.println(arrayList.get(i));
        }
    }
}
