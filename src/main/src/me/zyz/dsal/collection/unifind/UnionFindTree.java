package me.zyz.dsal.collection.unifind;

/**
 * @author yz
 */
public class UnionFindTree implements UnionFind {

    private int[] parent;

    public UnionFindTree(int size) {
        this.parent = new int[size];

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    private int find(int p) {
        while (p != parent[p]) {
            p = parent[p];
        }

        return p;
    }

    @Override
    public int size() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        parent[pRoot] = qRoot;
    }
}
