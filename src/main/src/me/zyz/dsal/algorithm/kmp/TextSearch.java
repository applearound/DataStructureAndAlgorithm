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

    public static int kmpSearch(CharSequence text, CharSequence words) {
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
}
