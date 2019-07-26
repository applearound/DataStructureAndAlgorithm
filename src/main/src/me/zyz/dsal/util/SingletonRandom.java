package me.zyz.dsal.util;

import java.util.Random;

/**
 * @author yz
 */
public class SingletonRandom {

    private static volatile Random random;

    private SingletonRandom() {
    }

    public static Random getInstance() {
        if (random == null) {
            synchronized (SingletonRandom.random) {
                if (random == null) {
                    random = new Random();
                }
            }
        }

        return random;
    }
}
