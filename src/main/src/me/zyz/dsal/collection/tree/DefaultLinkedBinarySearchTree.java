package me.zyz.dsal.collection.tree;

/**
 * @author yz
 */
public final class DefaultLinkedBinarySearchTree<K, V> extends AbstractLinkedBinarySearchTree<K, V, DefaultLinkedBinarySearchTree.DefaultBinaryNode<K, V>> {

    @Override
    public void add(K key, V value) {
        validateKey(key);

        DefaultBinaryNode<K, V> newNode = new DefaultBinaryNode<>(key, value);
        if (root == null) {
            root = newNode;
        } else {
            add1(root, newNode);
        }
        size++;
    }

    public void anotherAdd(K key, V value) {
        validateKey(key);
        root = add0(root, new DefaultBinaryNode<>(key, value));
        size++;
    }

    private DefaultBinaryNode<K, V> add0(DefaultBinaryNode<K, V> root, DefaultBinaryNode<K, V> addingNode) {
        if (root == null) {
            return addingNode;
        }

        int compareNumber = compareKey(addingNode.key(), root.key());
        if (compareNumber < 0) {
            root.setLeft(add0(root.left(), addingNode));
        } else if (compareNumber > 0) {
            root.setRight(add0(root.right(), addingNode));
        }

        return root;
    }

    private void add1(DefaultBinaryNode<K, V> root, DefaultBinaryNode<K, V> addingNode) {
        int compareNumber = compareKey(addingNode.key(), root.key());

        if (compareNumber < 0) {
            if (root.left() == null) {
                root.setLeft(addingNode);
                return;
            }

            add1(root.left(), addingNode);
        } else if (compareNumber > 0) {
            if (root.right() == null) {
                root.setRight(addingNode);
                return;
            }

            add1(root.right(), addingNode);
        }
    }

    public K removeMin() {
        K ret = minimumKey();

        removeMin0(root);
        size--;

        return ret;
    }

    private DefaultBinaryNode<K, V> removeMin0(DefaultBinaryNode<K, V> node) {
        if (node.left() == null) {
            DefaultBinaryNode<K, V> rightNode = node.right();
            node.purgeRight();
            return rightNode;
        }

        node.setLeft(removeMin0(node.left()));
        return node;
    }

    public K removeMax() {
        K ret = maximumKey();

        removeMax0(root);
        size--;

        return ret;
    }

    private DefaultBinaryNode<K, V> removeMax0(DefaultBinaryNode<K, V> node) {
        if (!node.hasRight()) {
            DefaultBinaryNode<K, V> leftNode = node.left();
            node.purgeLeft();
            return leftNode;
        }

        node.setRight(removeMax0(node.right()));
        return node;
    }

    public void remove(K k) {
        if (k == null) {
            throw new IllegalArgumentException("null value is not acceptable.");
        }
        root = remove0(root, k);
        size--;
    }

    private DefaultBinaryNode<K, V> remove0(DefaultBinaryNode<K, V> node, K k) {
        // 空树返回空
        if (node == null) {
            return null;
        }

        int compareNumber = compareKey(k, node.key());

        if (compareNumber < 0) {
            node.setLeft(remove0(node.left(), k));
            return node;
        } else if (compareNumber > 0) {
            node.setRight(remove0(node.right(), k));
            return node;
        } else {
            if (!node.hasLeft()) {
                DefaultBinaryNode<K, V> rightNode = node.right();
                node.purgeRight();
                return rightNode;
            }

            if (!node.hasRight()) {
                DefaultBinaryNode<K, V> leftNode = node.left();
                node.purgeLeft();
                return leftNode;
            }

            DefaultBinaryNode<K, V> successor = minimumNode(node.right());
            successor.setRight(removeMin0(node.right()));
            successor.setLeft(node.left());

            node.purgeLeft();
            node.purgeRight();

            return successor;
        }
    }


    static class DefaultBinaryNode<K, V> extends AbstractBinaryNode<K, V, DefaultBinaryNode<K, V>> {
        DefaultBinaryNode(K key, V value) {
            super(key, value);
        }
    }

    public static void main(String[] args) {
        DefaultLinkedBinarySearchTree<Integer, Integer> defaultLinkedBinarySearchTree = new DefaultLinkedBinarySearchTree<>();
        defaultLinkedBinarySearchTree.add(2, 2);
        defaultLinkedBinarySearchTree.add(1, 1);
        defaultLinkedBinarySearchTree.add(3, 3);
        defaultLinkedBinarySearchTree.add(10, 10);
        defaultLinkedBinarySearchTree.add(5, 5);
        defaultLinkedBinarySearchTree.add(4, 4);

        defaultLinkedBinarySearchTree.preOrderNR();
        System.out.println();
        defaultLinkedBinarySearchTree.inOrderNR();
        System.out.println();
        defaultLinkedBinarySearchTree.postOrderNR();
        System.out.println();
        defaultLinkedBinarySearchTree.levelOrder();
    }
}
