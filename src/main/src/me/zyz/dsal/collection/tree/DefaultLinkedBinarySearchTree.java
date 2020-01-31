package me.zyz.dsal.collection.tree;

/**
 * @author yz
 */
public final class DefaultLinkedBinarySearchTree<K, V> extends AbstractLinkedBinarySearchTree<K, V, DefaultLinkedBinarySearchTree.DefaultBinaryNode<K, V>> {

    @Override
    public void add(K key, V value) {
        validateKey(key);

        if (root == null) {
            root = new DefaultBinaryNode<>(key, value);
        } else {
            add1(root, key, value);
        }
        size++;
    }

    private void add1(DefaultBinaryNode<K, V> root, K key, V value) {
        int compareNumber = compareKey(key, root.key());

        if (compareNumber < 0) {
            if (root.left() == null) {
                root.setLeft(new DefaultBinaryNode<>(key, value));
                return;
            }
            add1(root.left(), key, value);
        } else if (compareNumber > 0) {
            if (root.right() == null) {
                root.setRight(new DefaultBinaryNode<>(key, value));
                return;
            }

            add1(root.right(), key, value);
        } else {
            root.setValue(value);
        }
    }

    public void anotherAdd(K key, V value) {
        root = add0(root, validateKey(key), value);
        size++;
    }

    private DefaultBinaryNode<K, V> add0(DefaultBinaryNode<K, V> root, K key, V value) {
        if (root == null) {
            return new DefaultBinaryNode<>(key, value);
        }

        int compareNum = compareKey(key, root.key());
        if (compareNum < 0) {
            root.setLeft(add0(root.left(), key, value));
        } else if (compareNum > 0) {
            root.setRight(add0(root.right(), key, value));
        } else {
            root.setValue(value);
        }

        return root;
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
            if (node.noLeft()) {
                DefaultBinaryNode<K, V> rightNode = node.right();
                node.purgeRight();
                return rightNode;
            }

            if (node.noRight()) {
                DefaultBinaryNode<K, V> leftNode = node.left();
                node.purgeLeft();
                return leftNode;
            }

            DefaultBinaryNode<K, V> successor = minimumNode(node.right());
            successor.setRight(removeMin0(node.right()));
            successor.setLeft(node.left());

            node.clearAllRef();

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

        defaultLinkedBinarySearchTree.preOrderNr();
        System.out.println();
        defaultLinkedBinarySearchTree.inOrderNr();
        System.out.println();
        defaultLinkedBinarySearchTree.postOrderNr();
        System.out.println();
        defaultLinkedBinarySearchTree.levelOrder();
    }
}
