package me.zyz.dsal.algorithm.hanoi;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HanoiClassic implements Hanoi {
    private final int    n;
    private final String from;
    private final String to;
    private final String aux;

    public HanoiClassic(int n, @NonNull String from, @NonNull String to, @NonNull String aux) {
        this.n    = n;
        this.from = from;
        this.to   = to;
        this.aux  = aux;
    }

    private static int move(int n, String from, String to, String aux) {
        if (n == 1) {
            log.info("从 {} 移动盘片 1 至 {}", from, to);
            return 1;
        }

        int l = move(n - 1, from, aux, to);

        log.info("从 {} 移动盘片 {} 至 {}", from, n, to);

        int r = move(n - 1, aux, to, from);

        return l + r + 1;
    }

    @Override
    public int move() {
        return move(n, from, to, aux);
    }
}
