package me.zyz.dsal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

@Slf4j
class GenericTest {
    static int[][] arr = new int[10000][10000];

    static {
        for (int[] intArr : arr) {
            Arrays.fill(intArr, 1);
        }
    }

    static class Holder {
        public int result;
    }

    interface Operator {
        void operate(Holder holder);
    }

    static Stream<Operator> operatorFactory() {
        return Stream.of(
                GenericTest::test00,
                GenericTest::test11,
                GenericTest::test22,
                GenericTest::test33,
                GenericTest::test44
        );
    }

    @MethodSource(value = "operatorFactory")
    @ParameterizedTest
    void test(final Operator op) {
        final Holder holder = new Holder();
        op.operate(holder);
        log.debug("result {}", holder.result);
    }


    static void test00(final Holder holder) {
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 10000; j++) {
                holder.result += arr[j][i];
            }
        }
    }

    static void test11(final Holder holder) {
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 10000; j++) {
                holder.result += arr[i][j];
            }
        }
    }

    static void test22(final Holder holder) {
        int result = 0;
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 10000; j++) {
                result += arr[i][j];
            }
        }
        holder.result = result;
    }

    static void test33(final Holder holder) {
        int result = 0;
        for (int i = 0; i < 10000; i++) {
            int[] now = arr[i];
            for (int j = 0; j < 10000; j++) {
                result += now[j];
            }
        }
        holder.result = result;
    }

    static void test44(final Holder holder) {
        int result = 0;
        for (int i = 0; i < 10000; i++) {
            int[] now = arr[i];
            for (int j = 0; j < 10000; j += 2) {
                final int v = now[j] + now[j + 1];
                result += v;
            }
        }
        holder.result = result;
    }
}
