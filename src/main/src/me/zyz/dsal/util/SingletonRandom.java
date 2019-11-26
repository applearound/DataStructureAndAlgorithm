package me.zyz.dsal.util;

import java.util.Random;

/**
 * @author yz
 */
public class SingletonRandom {

    private static Random random;

    private SingletonRandom() {
    }

    private static class RandomHolder {
        private static Random random = new Random();
    }

    public static Random getInstance() {
        if (random == null) {
            synchronized (SingletonRandom.class) {
                if (random == null) {
                    random = new Random();
                }
            }
        }

        return random;
    }
}
