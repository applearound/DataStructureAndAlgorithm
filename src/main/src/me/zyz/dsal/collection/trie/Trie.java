package me.zyz.dsal.collection.trie;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author yezhou
 */
public class Trie {
    class Node {
        private char c;
        private Map<Character, Node> nodeMap;

        public Node(char c) {
            this.c = c;
            this.nodeMap = new TreeMap<>();
        }
    }

    private Node root;

    public Trie() {
        this.root = new Node('\0');
    }

    public void add(CharSequence charSequence) {
        Node currentNode = root;
        for (int i = 0; i < charSequence.length(); i++) {
            Node tempNode = currentNode.nodeMap.get(charSequence.charAt(i));
            if (tempNode != null) {
                currentNode = tempNode;
            } else {
                Node node = new Node(charSequence.charAt(i));
                currentNode.nodeMap.put(charSequence.charAt(i), node);
                currentNode = node;
            }
        }
    }


    public static void main(String[] args) {

    }
}
