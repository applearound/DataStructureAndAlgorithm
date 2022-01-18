package me.zyz.dsal.collection.tree.segment;

/**
 * @author yz
 */
public interface Merger<E> {
    /**
     * 左、右节点进行某种合并的二元运算
     *
     * @param left 左节点
     * @param right 右节点
     * @return
     */
    E merge(E left, E right);
}
