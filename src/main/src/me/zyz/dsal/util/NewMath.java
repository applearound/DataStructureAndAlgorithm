package me.zyz.dsal.util;

/**
 * @author yezhou
 */
public class NewMath {
    private NewMath() {
    }

    public static final double LN_2 = Math.log(2);

    public static double log2(double num) {
        return Math.log(num) / LN_2;
    }

    public static int pow2(int exp) {
        if (exp < 0 || exp > 30) {
            throw new IllegalArgumentException("exp not in range [0, 30]");
        }
        return 1 << exp;
    }
}
