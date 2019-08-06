package me.zyz.dsal.collection.tree.trie;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author yezhou
 */
public class Trie {
    private static class Node {
        private Map<Character, Node> nodeMap;
        private boolean isWord;

        Node() {
            this.nodeMap = new TreeMap<>();
            this.isWord = false;
        }

        void beWord() {
            isWord = true;
        }

        boolean isWord() {
            return isWord;
        }
    }

    private Node root;

    public Trie() {
        this.root = new Node();
    }

    public void add(CharSequence sequence) {
        Node currentNode = root;
        for (int i = 0; i < sequence.length(); i++) {
            Character c = sequence.charAt(i);

            Node tempNode = currentNode.nodeMap.get(c);
            if (tempNode == null) {
                tempNode = new Node();
                currentNode.nodeMap.put(sequence.charAt(i), tempNode);
            }
            currentNode = tempNode;
        }
        currentNode.beWord();
    }

    public boolean contains(CharSequence sequence) {
        Node currentNode = root;
        for (int i = 0; i < sequence.length(); i++) {
            currentNode = currentNode.nodeMap.get(sequence.charAt(i));
            if (currentNode == null) {
                return false;
            }
        }

        return currentNode.isWord();
    }
}
