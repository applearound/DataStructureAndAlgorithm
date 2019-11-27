//package me.zyz.dsal.collection.tree;
//
//import java.util.Arrays;
//import java.util.Comparator;
//
//public class ArrayBinarySearchTree<K, V> implements BinarySearchTree<K, V> {
//    private static final int INITIAL_SIZE = 16;
//    private final Comparator<K> comparator;
//
//    private Object[] innerArray;
//    private int size;
//
//    public ArrayBinarySearchTree() {
//        this(null);
//    }
//
//    public ArrayBinarySearchTree(Comparator<K> comparator) {
//        this.innerArray = new Object[INITIAL_SIZE];
//        this.size = 0;
//        this.comparator = comparator;
//    }
//
//    @Override
//    public int size() {
//        return size;
//    }
//
//    public int capacity() {
//        return innerArray.length;
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return size == 0;
//    }
//
//    @Override
//    public void add(K key, V value) {
//        validateKeyValue(key, value);
//        if (((size + 1) << 1) + 1 > capacity()) {
//            resize();
//        }
//
//        ArrayBinaryNode newNode = new ArrayBinaryNode(key, value);
//        if (size == 0) {
//            innerArray[0] = newNode;
//        } else {
//            add0(0, newNode);
//        }
//
//        size++;
//    }
//
//    @Override
//    public V get(K key) {
//        return null;
//    }
//
//    @Override
//    public boolean contains(K key) {
//        return false;
//    }
//
//    private void add0(int rootIndex, ArrayBinaryNode addingNode) {
//        int compareNumber = compare(addingNode.key, node(rootIndex).key);
//
//        if (compareNumber < 0) {
//            int leftNodeIndex = leftNodeIndex(rootIndex);
//            if (leftNodeIndex(rootIndex) >= size) {
//                innerArray[leftNodeIndex] = addingNode;
//                return;
//            }
//
//            add0(leftNodeIndex, addingNode);
//        } else if (compareNumber > 0) {
//            int rightNodeIndex = rightNodeIndex(rootIndex);
//            if (rightNodeIndex >= size) {
//                innerArray[rightNodeIndex] = addingNode;
//                return;
//            }
//
//            add0(rightNodeIndex, addingNode);
//        }
//    }
//
//    private void resize() {
//        int newLength = innerArray.length + (innerArray.length >> 1);
//        if (innerArray.length == newLength) {
//            newLength = innerArray.length * 2;
//        }
//        innerArray = Arrays.copyOf(innerArray, newLength);
//    }
//
//    private void validateKeyValue(K key, V value) {
//        if (key == null) {
//            throw new IllegalArgumentException("key cannot be null");
//        }
//    }
//
//    private int compare(K k1, K k2) {
//        return comparator == null ? ((Comparable<K>) k1).compareTo(k2) :
//                comparator.compare(k1, k2);
//    }
//
//    private static int leftNodeIndex(int index) {
//        return (index >> 1) + 1;
//    }
//
//    private static int rightNodeIndex(int index) {
//        return (index >> 1) + 2;
//    }
//
//    private ArrayBinaryNode node(int index) {
//        return (ArrayBinaryNode) innerArray[index];
//    }
//
//    private ArrayBinaryNode leftNode(int index) {
//        return (ArrayBinaryNode) innerArray[leftNodeIndex(index)];
//    }
//
//    private ArrayBinaryNode rightNode(int index) {
//        return (ArrayBinaryNode) innerArray[rightNodeIndex(index)];
//    }
//
//    private class ArrayBinaryNode {
//        private K key;
//        private V value;
//
//        public ArrayBinaryNode(K key, V value) {
//            this.key = key;
//            this.value = value;
//        }
//    }
//
//    public static void main(String[] args) {
//        ArrayBinarySearchTree<Integer, Integer> arrayBinarySearchTree = new ArrayBinarySearchTree<>();
//        arrayBinarySearchTree.add(2, 2);
//        arrayBinarySearchTree.add(1, 1);
//        arrayBinarySearchTree.add(3, 3);
//        System.out.println();
//    }
//}
