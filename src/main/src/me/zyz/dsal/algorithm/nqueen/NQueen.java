package me.zyz.dsal.algorithm.nqueen;

/**
 *
 */
public class NQueen {
    private static final int SIZE = 10;
    private static int count = 0;
    private static boolean[][] pan = new boolean[SIZE][SIZE];

    public static void main(String[] args) {
        init();
        cal(0);
        System.out.println(count);
    }

    public static boolean cal(int i) {
        if (i == SIZE) {
            count++;
            return true;
        }

        boolean isSet = false;
        for (int j = 0; j < SIZE; j++) {
            if (check(i, j)) {
                pan[i][j] = true;
                isSet = cal(i + 1);
                pan[i][j] = false;
            }
        }
        return isSet;
    }

    public static void print() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print((pan[i][j] ? 1 : 0) + "\t");
            }
            System.out.println();
        }
    }

    public static void init() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                pan[i][j] = false;
            }
        }
    }

    public static boolean checkOutOfBound(int i, int j) {
        if (i < 0 || j < 0 || i >= SIZE || j >= SIZE) {
            return true;
        }
        return false;
    }

    public static boolean check(int i, int j) {
        for (int k = 0; k < SIZE; k++) {
            if (pan[i][k]) {
                return false;
            }
        }

        for (int k = 0; k < SIZE; k++) {
            if (pan[k][j]) {
                return false;
            }
        }

        int tempI = i;
        int tempJ = j;
        for (; !checkOutOfBound(tempI, tempJ); tempI--, tempJ--) {
            if (pan[tempI][tempJ]) {
                return false;
            }
        }

        tempI = i;
        tempJ = j;
        for (; !checkOutOfBound(tempI, tempJ); tempI++, tempJ++) {
            if (pan[tempI][tempJ]) {
                return false;
            }
        }

        tempI = i;
        tempJ = j;
        for (; !checkOutOfBound(tempI, tempJ); tempI++, tempJ--) {
            if (pan[tempI][tempJ]) {
                return false;
            }
        }

        tempI = i;
        tempJ = j;
        for (; !checkOutOfBound(tempI, tempJ); tempI--, tempJ++) {
            if (pan[tempI][tempJ]) {
                return false;
            }
        }

        return true;
    }
}
