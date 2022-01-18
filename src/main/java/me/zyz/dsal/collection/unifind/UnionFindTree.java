package me.zyz.dsal.collection.unifind;

/**
 * @author yz
 */
public class UnionFindTree implements UnionFind {

    private int[] parent;
    private int[] ranks;

    public UnionFindTree(int size) {
        this.parent = new int[size];
        this.ranks = new int[parent.length];

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            ranks[i] = 1;
        }
    }

    private int find(int p) {
        while (p != parent[p]) {
            p = parent[p];
        }

        return p;
    }

    private int height(int p) {
        return ranks[p];
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

        if (height(pRoot) < height(qRoot)) {
            parent[pRoot] = qRoot;
        } else if (height(pRoot) > height(qRoot)) {
            parent[qRoot] = pRoot;
        } else {
            parent[pRoot] = qRoot;
            ranks[qRoot]++;
        }
    }
}
