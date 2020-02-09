package me.zyz.dsal.algorithm.kmp;

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

    public static int betterRoughSearch(CharSequence text, CharSequence words) {
        int currentStartIndex = 0;
        while (currentStartIndex <= text.length() - words.length()) {
            if (text.charAt(currentStartIndex) != words.charAt(0)) {
                currentStartIndex++;
                continue;
            }

            int nextRunIndex = currentStartIndex + 1;

            int j = 1;
            for (; j < words.length(); j++) {
                if (j > 1 && nextRunIndex == currentStartIndex + 1 && text.charAt(currentStartIndex + j) == words.charAt(0)) {
                    nextRunIndex = currentStartIndex + j;
                }
                if (text.charAt(currentStartIndex + j) != words.charAt(j)) {
                    break;
                }
            }

            if (j == words.length()) {
                return currentStartIndex;
            }

            currentStartIndex = nextRunIndex;
        }
        return -1;
    }

    public static int kmpSearch(CharSequence text, CharSequence words) {
        int[] supportArray = buildSupportArray(words);

        int j = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != words.charAt(j)) {
                if (j != 0) {
                    i--;
                    j = supportArray[j - 1];
                }

                continue;
            }
            j++;
            if (j == words.length()) {
                return i - j + 1;
            }
        }

        return -1;
    }

    private static int[] buildSupportArray(CharSequence seq) {
        int[] supportArray = new int[seq.length()];
        supportArray[0] = 0;

        int j = 0;
        for (int i = 1; i < seq.length(); i++) {
            if (seq.charAt(i) == seq.charAt(j)) {
                supportArray[i + 1] = ++j;
                continue;
            }

            while (j != 0) {
                j = supportArray[j - 1];
                if (seq.charAt(i) == seq.charAt(j)) {
                    break;
                }
            }
            supportArray[i + 1] = ++j;
        }

        return supportArray;
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append('a');
        }
        sb.append('b');

//        long l = System.nanoTime();
//        System.out.println(roughSearch(sb, "aaaaaaaaaaaaaaaab"));
//        System.out.println((System.nanoTime() - l) / 1_000_000_000.0);

        long l1 = System.nanoTime();
        System.out.println(kmpSearch(sb, "abcaby"));
        System.out.println((System.nanoTime() - l1) / 1_000_000_000.0);
    }

}
