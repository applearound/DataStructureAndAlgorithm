package me.zyz.dsal.algorithm.textsearch;

/**
 * @author yezhou
 */
public class TextSearch {
    public static int roughSearch(CharSequence text, CharSequence words) {
        for (int i = 0; i <= text.length() - words.length(); i++) {
            int j = 0;
            for (; j < words.length(); j++) {
                if (text.charAt(i + j) != words.charAt(j)) {
                    break;
                }
            }
            if (j == words.length()) {
                return i;
            }
        }
        return -1;
    }

    public static int kmpSearch(CharSequence text, CharSequence words) {
        int m = text.length();
        int n = words.length();

        if (m >= n) {
            int[] supportArray = buildSupportArray(words);

            int i = 0;
            int j = 0;
            while (i < m) {
                if (text.charAt(i) != words.charAt(j)) {
                    if (supportArray[j] == -1) {
                        ++i;
                    } else {
                        j = supportArray[j];
                    }
                } else if (j < n - 1) {
                    ++i;
                    ++j;
                } else {
                    return i - j;
                }
            }
        }

        return -1;
    }

    private static int[] buildSupportArray(CharSequence seq) {
        int[] supportArray = new int[seq.length()];
        supportArray[0] = 0;

        int i = 1;
        int j = 0;
        while (i < seq.length()) {
            if (seq.charAt(i) == seq.charAt(j)) {
                supportArray[i++] = ++j;
            } else if (j != 0) {
                j = supportArray[j - 1];
            } else {
                supportArray[i++] = 0;
            }
        }

        System.arraycopy(supportArray, 0, supportArray, 1, supportArray.length - 1);
        supportArray[0] = -1;

        return supportArray;
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000000000; i++) {
            sb.append('a');
        }
        sb.append('b');

        long l = System.nanoTime();
        System.out.println(roughSearch(sb, "aaaaaaaaab"));
        System.out.println((System.nanoTime() - l) / 1_000_000_000.0);

        long l1 = System.nanoTime();
        System.out.println(kmpSearch(sb, "aaaaaaaaab"));
        System.out.println((System.nanoTime() - l1) / 1_000_000_000.0);
    }

}
