package me.zyz.dsal.algorithm.nqueen;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NQueenTest {
    @ParameterizedTest
    @CsvSource({
            "1, 1",
            "2, 0",
            "3, 0",
            "4, 2",
            "5, 10",
            "6, 4",
            "7, 40",
            "8, 92",
            "9, 352",
            "10, 724",
            "11, 2680",
            "12, 14200",
    })
    void calculate(final int size, final int actualNum) {
        final NQueen nQueen = new NQueen(size);

        assertEquals(actualNum, nQueen.calculate(), String.format("%d error", size));
    }

    long N = 0;
    long count = 0;

    void dfs(final long row, final long col, final long dl, final long dr) {
        if (row >= N) {
            count++;
            return;
        }

        long map = ((1L << N) - 1) & ~(col | dl | dr);
        while (map != 0) {
            final long bit = map ^ (map & map - 1);
            map -= bit;
            dfs(row + 1, col | bit, (dl | bit) >> 1, (dr | bit) << 1);
        }
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1",
            "2, 0",
            "3, 0",
            "4, 2",
            "5, 10",
            "6, 4",
            "7, 40",
            "8, 92",
            "9, 352",
            "10, 724",
            "11, 2680",
            "12, 14200",
    })
    void test(final int size, final int actualNum) {
        N = size;
        count = 0;

        dfs(0, 0, 0, 0);

        assertEquals(actualNum, count, String.format("%d error", size));
    }
}
