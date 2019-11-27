package me.zyz.dsal;

import java.util.*;

public class Test {

    private static final Random random = new Random();

    @org.junit.jupiter.api.Test
    public void test() {
        int times = Short.MAX_VALUE * Byte.MAX_VALUE;
        int bound = 3;
        Map<Integer, Integer> counterMap = new HashMap<>(100);

        for (int i = 0; i < times; i++) {
            Set<Integer> intSet = new HashSet<>();
            int count = 0;
            while (intSet.size() != bound) {
                intSet.add(random.nextInt(bound));
                count++;
            }

            counterMap.put(count, counterMap.getOrDefault(count, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> integerIntegerEntry : counterMap.entrySet()) {
            System.out.println("Key: " + integerIntegerEntry.getKey() + ", Value: " + integerIntegerEntry.getValue() + ", Present: " + (double) integerIntegerEntry.getValue() / times * 100 + "%");
        }
    }
}
