package me.zyz.dsal;

import me.zyz.dsal.tree.BstTree;

/**
 * @author zyz
 */
public class Main {

    public static void main(String[] args) {
        BstTree<Integer> bst = new BstTree<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums) {
            bst.add(num);
        }
        bst.preOrder();

        bst.preOrderNoRecursive();
    }
}
