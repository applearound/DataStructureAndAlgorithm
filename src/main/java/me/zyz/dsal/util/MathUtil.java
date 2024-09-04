package me.zyz.dsal.util;

/**
 * @author yezhou
 */
public final class MathUtil {
    public static final double LN_2 = Math.log(2);

    private MathUtil() {
    }

    public static double log2(final double x) {
        return Math.log(x) / LN_2;
    }

    public static int pow2(final int exp) {
        if (exp < 0 || exp > 30) {
            throw new ArithmeticException("exponent(0 <= exp <= 30)");
        }
        return 1 << exp;
    }

    public static int gcd(final int x, final int y) {
        if (x <= 0 || y <= 0) {
            throw new IllegalArgumentException("x, y must be greater than 0");
        }

        int a, b;
        if (x >= y) {
            a = x;
            b = y;
        } else {
            a = y;
            b = x;
        }

        int t;
        while (b != 0) {
            t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public static long gcd(final long x, final long y) {
        if (x <= 0 || y <= 0) {
            throw new IllegalArgumentException("x, y must be greater than 0");
        }

        long a, b;
        if (x >= y) {
            a = x;
            b = y;
        } else {
            a = y;
            b = x;
        }

        long t;
        while (b != 0) {
            t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public static int lcm(final int x, final int y) {
        if (x <= 0 || y <= 0) {
            throw new IllegalArgumentException("x, y must be greater than 0");
        }

        final int a, b;
        if (x >= y) {
            a = x;
            b = y;
        } else {
            a = y;
            b = x;
        }
        final long r = ((long) b) * (a / gcd(a, b));
        if (r != (int) r) {
            throw new ArithmeticException("integer overflow");
        }
        return (int) r;
    }

    public static long lcm(final long x, final long y) {
        if (x <= 0 || y <= 0) {
            throw new IllegalArgumentException("x, y must be greater than 0");
        }

        final long a, b;
        if (x >= y) {
            a = x;
            b = y;
        } else {
            a = y;
            b = x;
        }
        return Math.multiplyExact(b, a / gcd(a, b));
    }

    static int x = 0;
    static int y = 0;

    public static void main(String[] args) throws InterruptedException {
        var t1 = new Thread(() -> {
            x = 1;
            System.out.println(y);
        });

        var t2 = new Thread(() -> {
            y = 1;
            System.out.println(x);
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
