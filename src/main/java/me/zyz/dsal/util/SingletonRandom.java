package me.zyz.dsal.util;

import java.util.Random;

/**
 * @author yz
 */
public class SingletonRandom {
    private SingletonRandom() {
    }

    private static class RandomHolder {
        private static final Random random = new Random();
    }

    public static Random getInstance() {
        return RandomHolder.random;
    }
}
