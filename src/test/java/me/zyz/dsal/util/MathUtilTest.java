package me.zyz.dsal.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class MathUtilTest {
    @Test
    void log2() {
        assertEquals(0, MathUtil.log2(1), 1E-6);
        assertEquals(1, MathUtil.log2(2), 1E-6);
        assertEquals(2, MathUtil.log2(4), 1E-6);
        assertEquals(3, MathUtil.log2(8), 1E-6);
        assertEquals(4, MathUtil.log2(16), 1E-6);
        assertEquals(5, MathUtil.log2(32), 1E-6);
        assertEquals(6, MathUtil.log2(64), 1E-6);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 1",
            "1, 2",
            "2, 4",
            "3, 8",
            "4, 16",
            "5, 32",
            "6, 64",
            "7, 128",
            "8, 256",
            "9, 512",
            "10, 1024",
            "11, 2048",
            "12, 4096",
            "13, 8192",
            "14, 16384",
            "15, 32768",
            "16, 65536",
            "17, 131072",
            "18, 262144",
            "19, 524288",
            "20, 1048576",
            "21, 2097152",
            "22, 4194304",
            "23, 8388608",
            "24, 16777216",
            "25, 33554432",
            "26, 67108864",
            "27, 134217728",
            "28, 268435456",
            "29, 536870912",
            "30, 1073741824"
    })
    void pow2(final int exp, final int result) {
        assertEquals(result, MathUtil.pow2(exp));
    }
}
