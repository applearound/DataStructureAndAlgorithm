package me.zyz.dsal.collection.tree;

/**
 * @author yz
 */
interface BinarySearchTree<K, V, N extends BinarySearchTree.BinaryNode<K, V, N>> {
    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    void add(K key, V value);

    V findValue(K key);

    V getValue(K key);

    boolean contains(K key);

    interface BinaryNode<K, V, N extends BinaryNode<K, V, N>> {
        K key();

        V value();

        void setKey(K key);

        void setValue(V value);

        N left();

        N right();

        N parent();

        void setLeft(N left);

        void setRight(N right);

        void setParent(N parent);

        default boolean hasLeft() {
            return left() != null;
        }

        default boolean hasRight() {
            return right() != null;
        }

        default boolean noLeft() {
            return !hasLeft();
        }

        default boolean noRight() {
            return !hasRight();
        }

        default void purgeLeft() {
            setLeft(null);
        }

        default N clearLeft() {
            N oldLeft = left();
            purgeLeft();
            return oldLeft;
        }

        default void purgeRight() {
            setRight(null);
        }

        default N clearRight() {
            N oldRight = right();
            purgeRight();
            return oldRight;
        }

        default void purgeValue() {
            setValue(null);
        }

        default V clearValue() {
            V oldValue = value();
            purgeValue();
            return oldValue;
        }

        default void purgeKey() {
            setKey(null);
        }

        default K clearKey() {
            K oldKey = key();
            purgeKey();
            return oldKey;
        }

        default void purgeParent() {
            setParent(null);
        }

        default N clearParent() {
            N oldParent = parent();
            purgeParent();
            return oldParent;
        }

        default void clearAllRef() {
            purgeKey();
            purgeValue();
            purgeParent();
            purgeLeft();
            purgeRight();
        }

        default void swapChildren() {
            N temp = left();
            setLeft(right());
            setRight(temp);
        }
    }
}
