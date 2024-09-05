package me.zyz.dsal.algorithm.nqueen;

/**
 *
 */
public class NQueen {
    private final int         size;
    private final boolean[][] board;

    private int count = 0;

    public NQueen(final int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("size");
        }
        this.size  = size;
        this.board = new boolean[size][size];
    }

    public int calculate() {
        count = 0;

        cal(0);

        return count;
    }

    private boolean cal(final int i) {
        if (i == size) {
            count++;
            return true;
        }

        boolean isSet = false;
        for (int j = 0; j < size; j++) {
            if (check(i, j)) {
                board[i][j] = true;
                isSet       = cal(i + 1);
                board[i][j] = false;
            }
        }
        return isSet;
    }

    private boolean checkInBound(final int i, final int j) {
        return i >= 0 && j >= 0 && i < size && j < size;
    }

    private boolean check(final int i, final int j) {
        for (int k = 0; k < size; k++) {
            if (board[i][k]) {
                return false;
            }
        }

        for (int k = 0; k < size; k++) {
            if (board[k][j]) {
                return false;
            }
        }

        int tempI = i;
        int tempJ = j;
        for (; checkInBound(tempI, tempJ); tempI--, tempJ--) {
            if (board[tempI][tempJ]) {
                return false;
            }
        }

        tempI = i;
        tempJ = j;
        for (; checkInBound(tempI, tempJ); tempI++, tempJ++) {
            if (board[tempI][tempJ]) {
                return false;
            }
        }

        tempI = i;
        tempJ = j;
        for (; checkInBound(tempI, tempJ); tempI++, tempJ--) {
            if (board[tempI][tempJ]) {
                return false;
            }
        }

        tempI = i;
        tempJ = j;
        for (; checkInBound(tempI, tempJ); tempI--, tempJ++) {
            if (board[tempI][tempJ]) {
                return false;
            }
        }

        return true;
    }
}
