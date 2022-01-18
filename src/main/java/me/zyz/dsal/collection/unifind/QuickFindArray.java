package me.zyz.dsal.collection.unifind;

/**
 * @author yz
 */
public class QuickFindArray implements UnionFind {

    private int[] ids;

    public QuickFindArray(int size) {
        this.ids = new int[size];

        for (int i = 0; i < ids.length; i++) {
            ids[i] = i;
        }
    }

    private int find(int p) {
        return ids[p];
    }

    @Override
    public int size() {
        return ids.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pId = find(p);
        int qId = find(q);

        if (pId == qId) {
            return;
        }

        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == qId) {
                ids[i] = pId;
            }
        }
    }
}
