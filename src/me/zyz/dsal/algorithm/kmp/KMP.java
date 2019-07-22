package me.zyz.dsal.algorithm.kmp;

/**
 * @author yezhou
 */
public class KMP {
    public static void main(String[] args) {
        String a = "abcdef";
        String b= "cdf";

        int i = 0;
        while (i < a.length()) {
            int j = i;
            int k = 0;
            while (j < a.length() && k < b.length() && a.charAt(j) == b.charAt(k)) {
                j++;
                k++;
            }
            if (k == b.length()) {
                System.out.println("true");
                break;
            }

            i++;
        }
    }
}
